//
//  ProfileCard.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 02/06/2022.
//

import SwiftUI
import shared

struct ProfileCard: View {
    
    @Binding var user: Result<User>
    let onError: () -> Void
    
    var body: some View {
        if (user is ResultSuccess) {
            AsyncImage(url: URL(string: user.data!.profilePicture)) { image in
                image.resizable()
            } placeholder: {
                Image(systemName: "photo.fill")
                    .frame(width: 64, height: 64)
            }
            .frame(width: 256, height: 256)
            .cornerRadius(16)
            .overlay(profileDesc, alignment: .bottomLeading)
        } else if (user is ResultLoading) {
            ProgressView()
        } else {
            Image(systemName: "wifi.slash")
                .onTapGesture {
                    onError()
                }
        }
    }
    
    var profileDesc: some View {
        ZStack {
            Rectangle()
                .frame(
                    width: 256,
                    height: 128
                )
                .cornerRadius(16)
                .foregroundColor(.clear)
                .background(
                    LinearGradient(
                        gradient: Gradient(colors: [.white.opacity(0), .darkGray]),
                        startPoint: .center,
                        endPoint: .bottom
                    )
                ).overlay(alignment: .bottomLeading) {
                    VStack(alignment: .leading) {
                        HStack(alignment: .bottom) {
                            Text("\(user.data!.first),")
                                .fontWeight(.bold)
                            
                            Text("\(user.data!.age)")
                                .fontWeight(.light)
                        }
                        .foregroundColor(Color.white)
                        Text("\(user.data!.location.country), \(user.data!.location.city)")
                            .foregroundColor(Color.white)
                            .fontWeight(.light)
                    }
                }
        }
    }
}
