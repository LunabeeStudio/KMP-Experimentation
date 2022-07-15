//
//  UserRepositoryImpl.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 04/05/2022.
//

import Foundation
import shared
import SwiftUI

class UserRepositoryImpl: UserRepository {
    
    private let UserNetworkSource = Init().create()
    
    func getUsers() async -> Result<String, [User]> {
        return await withCheckedContinuation { continuation in
            UserNetworkSource.getUsersList (completionHandler: { data, error in
                if let result = data {
                    continuation.resume(returning: Result.Success(result.map({ UserDtoToModel(user: $0) })))
                }
                
                if let err = error as? NSError {
                    let errorType = err.userInfo["KotlinException"]
                    
                    if (errorType is ErrorException.Server) {
                        continuation.resume(returning: Result.Error(Strings.Network.Error.server))
                    } else if (errorType is ErrorException.Network) {
                        continuation.resume(returning: Result.Error(Strings.Network.Error.network))
                    } else {
                        continuation.resume(returning: Result.Error(Strings.Network.Error.unexpected))
                    }
                }
            })
        }
    }
}

