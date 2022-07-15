import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinKt.doInitKoin(enableNetworkLogs: false, appDeclaration: {_ in })
    }
    
    var body: some Scene {
        WindowGroup {
            HomeView()
        }
    }
}
