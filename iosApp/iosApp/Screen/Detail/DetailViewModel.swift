//
//  DetailViewModel.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 31/05/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesRxSwift

@MainActor
final class DetailViewModel: ObservableObject {
    let userRepo = HelperDi().userRepository
    
    @Published var liked: [User] = [User]()
    @Published var disliked: [User] = [User]()
    
    func getLikedUser() {
        let observable = createObservable(for: userRepo.getLikedUsersNative())
        
        _ = observable.subscribe(onNext: { value in
            Task {
                self.liked = value
            }
        })
    }
    
    func getDislikedUser() {
        let observable = createObservable(for: userRepo.getDislikedUsersNative())
        
        _ = observable.subscribe(onNext: { value in
            Task {
                self.disliked = value
            }
        })
    }
}
