//
//  DislikedColumn.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 07/06/2022.
//

import SwiftUI
import shared

struct DislikedColumn: View {
    let usersDisliked: [User]
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading) {
                ForEach(usersDisliked, id: \.id) {
                    UserRow(user: $0)
                }
            }
        }
    }
}

struct DislikedColumn_Previews: PreviewProvider {
    static var previews: some View {
        DislikedColumn(usersDisliked: [User]())
    }
}
