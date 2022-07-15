//
//  HomeView.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 31/05/2022.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @ObservedObject private var viewModel = HomeViewModel()
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                ProfileCard(user: $viewModel.user, onError: { viewModel.getUser(newUser: false) })
                ActionBar { (liked: Like) async in
                    if (viewModel.user is ResultSuccess) {
                        viewModel.user.data!.liked = liked.value
                        await viewModel.nextUser(user: viewModel.user.data!)
                    }
                }
                .navigationBarTitleDisplayMode(.inline)
                .navigationTitle(MR.strings().home_screen_title.desc().localized())
                .navigationBarItems(
                    trailing:
                        NavigationLink(destination: DetailView()) {
                            Image(systemName: "info.circle")
                        }
                )
            }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
