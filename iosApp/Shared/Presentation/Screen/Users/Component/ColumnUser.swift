//
//  ColumnUser.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 20/04/2022.
//

import SwiftUI

struct ColumnUser: View {
    
    @StateObject var store = UserStore()
    
    var body: some View {

        switch store.result {
        case .Loading:
            ProgressView()
                .task {
                    await store.getUsersList()
                }
        case .Error(let error):
            Text(error)
                .refreshable {
                    await store.getUsersList()
                }
        case .Success(let data):
            List(data) { item in
                RowUser(user: item)
            }
            .refreshable {
                await store.getUsersList()
            }
        }
    }
}


class UserStore: ObservableObject {
    
    private let userRepository = UserRepositoryImpl()
    
    @Published var result: Result<String, [User]> = Result.Loading
    
    func getUsersList() async {
        let data = await userRepository.getUsers()
        Task { @MainActor in
            result = data
        }
    }
}



struct ColumnUser_Previews: PreviewProvider {
    static var previews: some View {
        ColumnUser()
    }
}
