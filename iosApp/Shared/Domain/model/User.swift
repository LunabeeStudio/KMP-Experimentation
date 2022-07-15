//
//  User.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 04/05/2022.
//

import Foundation
import shared

struct User: Identifiable {
    let id: String
    let firstName: String
    let lastName: String
    let email: String
    let profilePicture: String
    
    func getName() -> String {
        return "\(lastName.uppercased()) \(firstName.capitalized)"
    }
}


func UserDtoToModel(user: UserDto) -> User {
    return User(
        id: user.login.uuid,
        firstName: user.name.first,
        lastName: user.name.last,
        email: user.email,
        profilePicture: user.picture.profile
    )
}
