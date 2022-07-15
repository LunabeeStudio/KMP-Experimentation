//
//  DetailView.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 31/05/2022.
//

import SwiftUI
import shared

struct DetailView: View {
    
    @ObservedObject private var viewModel = DetailViewModel()
    
    var body: some View {
        TabView {
            LikedColumn(usersLiked: viewModel.liked)
                .onAppear {
                    viewModel.getLikedUser()
                }
                .tabItem {
                    Label(MR.strings().detail_screen_tab_like.desc().localized(), systemImage: "heart.fill")
                }
            
            DislikedColumn(usersDisliked: viewModel.disliked)
                .onAppear {
                    viewModel.getDislikedUser()
                }
                .tabItem {
                    Label(MR.strings().detail_screen_tab_dislike.desc().localized(), systemImage: "xmark.circle")
                }
                .padding(4)
        }
    }
}

struct DetailView_Previews: PreviewProvider {
    static var previews: some View {
        DetailView()
    }
}
