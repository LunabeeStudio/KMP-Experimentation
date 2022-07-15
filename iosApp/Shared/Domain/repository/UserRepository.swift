//
//  UserRepository.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 04/05/2022.
//

import Foundation

protocol UserRepository {
    
    func getUsers() async -> Result<String, [User]>
    
}
