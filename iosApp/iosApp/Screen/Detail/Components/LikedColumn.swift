//
//  LikedColumn.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 07/06/2022.
//

import SwiftUI
import shared

struct LikedColumn: View {
    
    let usersLiked: [User]
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading) {
                ForEach(usersLiked, id: \.id) {
                    UserRow(user: $0)
                }
            }
        }
    }
}

struct LikedColumn_Previews: PreviewProvider {
    static var previews: some View {
        LikedColumn(usersLiked: [User]())
    }
}
