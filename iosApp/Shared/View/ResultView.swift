//
//  ResultView.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 14/04/2022.
//

import SwiftUI

struct ResultView: View {
    var result: Bool

    private let resultEmail = Strings.Result.Email.self
    
    var body: some View {
        NavigationView {
            Text((result) ? resultEmail.correct : resultEmail.wrong)
                .navigationTitle(Strings.Navigation.Title.result)
                .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct ResultView_Previews: PreviewProvider {
    static var previews: some View {
        ResultView(result: true)
    }
}
