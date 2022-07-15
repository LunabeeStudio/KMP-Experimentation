//
//  UserRow.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 07/06/2022.
//

import SwiftUI
import shared

struct UserRow: View {
    let user: User
    
    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(url: URL(string: user.profilePicture)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .frame(width: 48, height: 48)
                } else if phase.error != nil {
                    Label("", systemImage: "wifi.slash")
                        .labelStyle(.iconOnly)
                } else {
                    ProgressView()
                }
            }
            .frame(width: 48, height: 48)
            .clipShape(Circle())
            
            VStack(alignment: .leading) {
                Text(user.first)
                Text(user.last)
                    .font(.subheadline)
                    .fontWeight(.thin)
                    .lineLimit(1)
            }
            .padding(.leading, 2.0)
        }
        .padding(2.0)
    }
}
