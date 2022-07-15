//
//  usersScreen.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 04/05/2022.
//

import SwiftUI

struct UsersScreen: View {
    
    var body: some View {
        VStack {
            ColumnUser()
                .navigationTitle(Strings.Users.titleScreen)
                .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct UsersScreen_Previews: PreviewProvider {
    static var previews: some View {
        UsersScreen()
    }
}
