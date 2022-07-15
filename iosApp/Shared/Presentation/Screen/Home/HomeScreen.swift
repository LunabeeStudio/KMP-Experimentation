//
//  homeScreen.swift
//  network (iOS)
//
//  Created by Anthony Couhier on 04/05/2022.
//

import SwiftUI

struct HomeScreen: View {
    
    @State var navToUsersScreen = false
    
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink(destination: UsersScreen(), isActive: $navToUsersScreen) { EmptyView() }
                
                Button(Strings.Home.fetchData, action: { navToUsersScreen = !navToUsersScreen })
                    .buttonStyle(.borderedProminent)
                    .navigationTitle(Strings.Home.titleScreen)
                
            }
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
