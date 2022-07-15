package studio.lunabee.shared

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import lunabee.studio.sqldelight.UserDatabase
import org.koin.test.KoinTest
import studio.lunabee.shared.common.Like
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.di.source.createHttpClient
import studio.lunabee.shared.di.source.createJson
import studio.lunabee.shared.domain.model.Location
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.model.toModel
import studio.lunabee.shared.domain.repository.UserRepository
import studio.lunabee.shared.local.UserDatabaseHelper
import studio.lunabee.shared.remote.ErrorException
import studio.lunabee.shared.repository.UserRepositoryImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryTest : KoinTest {
    private lateinit var userRepository: UserRepository

    private val mockEngine: MockEngine = MockEngine {
        respond(
            content = ByteReadChannel("""[{"gender":"female","name":{"title":"Miss","first":"Sofia","last":"Lavigne"},"location":{"street":{"number":661,"name":"Dieppe Ave"},"city":"Sidney","state":"Saskatchewan","country":"Canada","postcode":"E8R 5E4","coordinates":{"latitude":"-63.4220","longitude":"162.7203"},"timezone":{"offset":"+6:00","description":"Almaty, Dhaka, Colombo"}},"email":"sofia.lavigne@example.com","login":{"uuid":"e84c16ce-7c96-44fd-af8e-df7f97362f0a","username":"tinyswan995","password":"liberty","salt":"YJzs81z9","md5":"2d759c1784b8a54b51e0f361727e47c3","sha1":"d31a1357bb9c78a347914ba645384a254306bbff","sha256":"c9af84b5ac8a270978756b406423c7569594f046184aa61e57b5e8fd54110c18"},"dob":{"date":"1969-10-17T04:15:39.735Z","age":53},"registered":{"date":"2018-11-11T08:52:43.588Z","age":4},"phone":"423-190-6017","cell":"762-906-3382","id":{"name":"","value":null},"picture":{"large":"https://randomuser.me/api/portraits/women/58.jpg","medium":"https://randomuser.me/api/portraits/med/women/58.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/women/58.jpg"},"nat":"CA"}]"""),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private val mockUser: User = User(
        id = "1",
        profilePicture = "url-to-profile-picture",
        first = "Undefined",
        last = "0",
        age = 42,
        location = Location(
            country = "France",
            city = "Lyon"
        ),
        nationality = "FR",
        liked = Like.Undefined.value
    )

    private var db: UserDatabaseHelper = UserDatabaseHelper(UserDatabase(testDbConnection()))

    private var client: HttpClient = createHttpClient(createJson(), false, mockEngine)

    private fun setupClient(newMockEngine: MockEngine) {
        client = createHttpClient(createJson(), false, newMockEngine)
        userRepository = UserRepositoryImpl(db, client)
    }

    @BeforeTest
    fun setup(): TestResult = runTest {
        db.deleteAll()
        userRepository = UserRepositoryImpl(db, client)
    }

    @Test
    fun `saveUser - Save new user`(): TestResult = runTest {
        userRepository.saveUser(mockUser)
        assertEquals(
            expected = 1,
            actual = db.selectAll().size,
            message = "Could not save user"
        )
    }

    @Test
    fun `getLastUser - One user with undefined like`(): TestResult = runTest {
        val mockLikedUser = mockUser.copy(
            id = "2",
            first = "Yes",
            last = "1",
            liked = Like.Yes.value
        )
        userRepository.saveUser(mockLikedUser)
        userRepository.saveUser(mockUser)
        assertEquals(
            expected = mockUser,
            actual = userRepository.getLastUser(),
            message = "Cannot find a user with undefined like"
        )
    }

    @Test
    fun `getLastUser - Zero user with undefined like`(): TestResult = runTest {
        val mockLikedUser = mockUser.copy(
            id = "2",
            first = "Yes",
            last = "1",
            liked = Like.Yes.value
        )
        userRepository.saveUser(mockLikedUser)
        assertNull(
            actual = userRepository.getLastUser(),
            message = "User with undefined like found"
        )
    }

    @Test
    fun `updateUser - Update user with undefined like to Yes`(): TestResult = runTest {
        userRepository.saveUser(mockUser)

        val retrievedUser: User = db.selectUserById(mockUser.id).toModel()

        retrievedUser.liked = Like.Yes.value

        userRepository.updateUser(retrievedUser)

        assertEquals(
            expected = Like.Yes.value,
            actual = db.selectUserById(mockUser.id).liked,
            message = "User has not been update to Yes like"
        )
    }

    @Test
    fun `getLikedUsers - Get all liked users with no one liked`(): TestResult = runTest {
        assertEquals(
            expected = 0,
            actual = userRepository.getLikedUsers().first().size,
            message = "User found, need 0 user"
        )
    }

    @Test
    fun `getLikedUsers - Get all liked users with two liked`(): TestResult = runTest {
        val one = mockUser.copy(id = "1", liked = Like.Yes.value)
        val two = mockUser.copy(id = "2", liked = Like.Yes.value)

        userRepository.saveUser(one)
        userRepository.saveUser(two)

        assertEquals(
            expected = 2,
            actual = userRepository.getLikedUsers().first().size,
            message = "User found, need 0 user"
        )
    }

    @Test
    fun `getDislikedUsers - Get all disliked users with no one disliked`(): TestResult = runTest {
        assertEquals(
            expected = 0,
            actual = userRepository.getDislikedUsers().first().size,
            message = "User found, need 0 user"
        )
    }

    @Test
    fun `getDislikedUsers - Get all disliked users with two disliked`(): TestResult = runTest {
        val one = mockUser.copy(id = "1", liked = Like.No.value)
        val two = mockUser.copy(id = "2", liked = Like.No.value)

        userRepository.saveUser(one)
        userRepository.saveUser(two)

        assertEquals(
            expected = 2,
            actual = userRepository.getDislikedUsers().first().size,
            message = "Not all user has been found, need 2 user"
        )
    }

    @Test
    fun `fetchNewUser - Success`(): TestResult = runTest {
        val resToExpect: Result<User> = Result.Success(User(
            id = "e84c16ce-7c96-44fd-af8e-df7f97362f0a",
            profilePicture = "https://randomuser.me/api/portraits/women/58.jpg",
            first = "Sofia",
            last = "Lavigne",
            age = 53,
            location = Location(
                city = "Sidney",
                country = "Canada",
            ),
            nationality = "CA",
        ))
        val resToTest: Result<User> = userRepository.fetchNewUser(0)

        assertIs<Result.Success<User>>(resToTest)
        assertEquals(resToExpect.data, resToTest.data)
    }

    @Test
    fun `fetchNewUser - serialization error`(): TestResult = runTest {
        val mockEngineResponseException: MockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""[{"MissingNameArguments":{"title":"Miss","first":"Sofia","last":"Lavigne"},"location":{"street":{"number":661,"name":"Dieppe Ave"},"city":"Sidney","state":"Saskatchewan","country":"Canada","postcode":"E8R 5E4","coordinates":{"latitude":"-63.4220","longitude":"162.7203"},"timezone":{"offset":"+6:00","description":"Almaty, Dhaka, Colombo"}},"email":"sofia.lavigne@example.com","login":{"uuid":"e84c16ce-7c96-44fd-af8e-df7f97362f0a","username":"tinyswan995","password":"liberty","salt":"YJzs81z9","md5":"2d759c1784b8a54b51e0f361727e47c3","sha1":"d31a1357bb9c78a347914ba645384a254306bbff","sha256":"c9af84b5ac8a270978756b406423c7569594f046184aa61e57b5e8fd54110c18"},"dob":{"date":"1969-10-17T04:15:39.735Z","age":53},"registered":{"date":"2018-11-11T08:52:43.588Z","age":4},"phone":"423-190-6017","cell":"762-906-3382","id":{"name":"","value":null},"picture":{"large":"https://randomuser.me/api/portraits/women/58.jpg","medium":"https://randomuser.me/api/portraits/med/women/58.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/women/58.jpg"},"nat":"CA"}]"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        setupClient(mockEngineResponseException)

        val resToTest: Result<User> = userRepository.fetchNewUser(0)

        assertIs<Result.Error<User>>(resToTest)
        assertIs<ErrorException.Server>(resToTest.ex)
    }

    @Test
    fun `fetchNewUser - Response error`(): TestResult = runTest {
        val mockEngineResponseException: MockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""[{"MissingNameArguments":{"title":"Miss","first":"Sofia","last":"Lavigne"},"location":{"street":{"number":661,"name":"Dieppe Ave"},"city":"Sidney","state":"Saskatchewan","country":"Canada","postcode":"E8R 5E4","coordinates":{"latitude":"-63.4220","longitude":"162.7203"},"timezone":{"offset":"+6:00","description":"Almaty, Dhaka, Colombo"}},"email":"sofia.lavigne@example.com","login":{"uuid":"e84c16ce-7c96-44fd-af8e-df7f97362f0a","username":"tinyswan995","password":"liberty","salt":"YJzs81z9","md5":"2d759c1784b8a54b51e0f361727e47c3","sha1":"d31a1357bb9c78a347914ba645384a254306bbff","sha256":"c9af84b5ac8a270978756b406423c7569594f046184aa61e57b5e8fd54110c18"},"dob":{"date":"1969-10-17T04:15:39.735Z","age":53},"registered":{"date":"2018-11-11T08:52:43.588Z","age":4},"phone":"423-190-6017","cell":"762-906-3382","id":{"name":"","value":null},"picture":{"large":"https://randomuser.me/api/portraits/women/58.jpg","medium":"https://randomuser.me/api/portraits/med/women/58.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/women/58.jpg"},"nat":"CA"}]"""),
                status = HttpStatusCode.fromValue(501),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        setupClient(mockEngineResponseException)

        val resToTest: Result<User> = userRepository.fetchNewUser(0)

        assertIs<Result.Error<User>>(resToTest)
        assertIs<ErrorException.Server>(resToTest.ex)
    }

    @Test
    fun `fetchNewUser - Timeout`(): TestResult = runTest {
        val mockEngineIOException: MockEngine = MockEngine {
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.RequestTimeout,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        setupClient(mockEngineIOException)

        val resToTest: Result<User> = userRepository.fetchNewUser(0)

        assertIs<Result.Error<User>>(resToTest)
        assertIs<ErrorException.Network>(resToTest.ex)
    }

    @Test
    fun `fetchNewUser - Unexpected`(): TestResult = runTest {
        val mockEngineException: MockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""[{"gender":"female","name":{"title":"Miss","first":"Sofia","last":"Lavigne"},"location":{"street":{"number":661,"name":"Dieppe Ave"},"city":"Sidney","state":"Saskatchewan","country":"Canada","postcode":"E8R 5E4","coordinates":{"latitude":"-63.4220","longitude":"162.7203"},"timezone":{"offset":"+6:00","description":"Almaty, Dhaka, Colombo"}},"email":"sofia.lavigne@example.com","login":{"uuid":"e84c16ce-7c96-44fd-af8e-df7f97362f0a","username":"tinyswan995","password":"liberty","salt":"YJzs81z9","md5":"2d759c1784b8a54b51e0f361727e47c3","sha1":"d31a1357bb9c78a347914ba645384a254306bbff","sha256":"c9af84b5ac8a270978756b406423c7569594f046184aa61e57b5e8fd54110c18"},"dob":{"date":"1969-10-17T04:15:39.735Z","age":53},"registered":{"date":"2018-11-11T08:52:43.588Z","age":4},"phone":"423-190-6017","cell":"762-906-3382","id":{"name":"","value":null},"picture":{"large":"https://randomuser.me/api/portraits/women/58.jpg","medium":"https://randomuser.me/api/portraits/med/women/58.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/women/58.jpg"},"nat":"CA"}]"""),
                status = HttpStatusCode.SeeOther,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }

        setupClient(mockEngineException)

        val resToTest: Result<User> = userRepository.fetchNewUser(0)

        assertIs<Result.Error<User>>(resToTest)
        assertIs<ErrorException.Unexpected>(resToTest.ex)
    }
}
