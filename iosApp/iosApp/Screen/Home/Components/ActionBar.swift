//
//  ActionBar.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 02/06/2022.
//

import SwiftUI
import shared

struct ActionBar: View {
    
    let isLike: (Like) async -> Void
    
    var body: some View {
        HStack {
            Spacer()
            Button {
                Task {
                    await isLike(Like.No())
                }
            } label: {
                Image(systemName: "xmark.circle")
                    .foregroundColor(.red)
                    .imageScale(.large)
            }
            Spacer()
            Button {
                Task {
                    await isLike(Like.Yes())
                }
            } label: {
                Image(systemName: "heart.fill")
                    .foregroundColor(.green)
                    .imageScale(.large)
            }
            Spacer()
        }
        .padding(.top, 8.0)
    }
}

struct ActionBar_Previews: PreviewProvider {
    static var previews: some View {
        ActionBar(isLike: {like in
            return
        })
    }
}
