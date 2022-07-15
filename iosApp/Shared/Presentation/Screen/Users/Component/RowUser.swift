//
//  RowUser.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 20/04/2022.
//

import SwiftUI

struct RowUser: View {
    
    var user: User
    
    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(url: URL(string: user.profilePicture)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .frame(width: 48, height: 48)
                } else if phase.error != nil {
                    Text(Strings.Network.Error.loading)
                } else {
                    ProgressView()
                }
            }
            .frame(width: 48, height: 48)
            .clipShape(Circle())
            
            VStack(alignment: .leading) {
                Text(user.getName())
                Text(user.email)
                    .font(.subheadline)
                    .fontWeight(.thin)
                    .lineLimit(1)
            }
            .padding(.leading, 2.0)
        }
        .padding(2.0)
    }
}
