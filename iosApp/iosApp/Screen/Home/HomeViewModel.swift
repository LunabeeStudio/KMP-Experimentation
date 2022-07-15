//
//  HomeViewModel.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 31/05/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesRxSwift
import KMPNativeCoroutinesCombine

@MainActor final class HomeViewModel: ObservableObject {
    
    let userRepo = HelperDi().userRepository

    @Published var user: Result<User> = ResultLoading()
    private var lastUserLoaded: Int32 = -1
    
    init() {
        getUser(newUser: true)
    }
    
    func getUser(newUser: Bool) {
        if (newUser) {
            lastUserLoaded = Int32.random(in: 0..<499)
        }
        
        let observable = createObservable(for: HelperDi().getNewUserNative(page: lastUserLoaded))
        _ = observable.subscribe(onNext: { value in
            Task { @MainActor in
                self.user = value
            }
        })
    }
    
    func nextUser(user: User) async {
        do {
            try await userRepo.updateUser(user: user)
            self.user = ResultLoading<User>()
            self.user = try await HelperDi().getNewUser(page: Int32.random(in: 0..<499))
        } catch {
            print("ERROR IN HOME VM")
        }
    }
}

