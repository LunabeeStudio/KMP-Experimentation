//
//  Result.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 05/05/2022.
//

import Foundation

enum Result<S, U> {
    case Loading
    case Success(U)
    case Error(S)
}
