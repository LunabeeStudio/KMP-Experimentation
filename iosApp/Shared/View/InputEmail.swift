//
//  InputEmail.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 14/04/2022.
//

import SwiftUI
import shared

struct InputEmail: View {
    
    @State private var email = ""
    @State private var isShowingDetailView = false
    @FocusState private var emailFieldIsFocused: Bool
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {

                NavigationLink(destination: ResultView(result: CheckEmailKt.checkEmail(email)), isActive: $isShowingDetailView) {
                    EmptyView()
                }
                Text(Strings.Request.Person.email)
                TextField(Strings.Person.email, text: $email)
                    .focused($emailFieldIsFocused)
                    .disableAutocorrection(true)
                    .onSubmit {
                        navigateToResult()
                    }
                Button(Strings.Utils.verify, action: navigateToResult)
                    .buttonStyle(.borderedProminent)
            }
            .padding()
            .navigationTitle(Strings.Navigation.Title.home)
            }
    }
    
    private func navigateToResult() {
        isShowingDetailView = !isShowingDetailView
    }
}

struct InputEmail_Previews: PreviewProvider {
    static var previews: some View {
        InputEmail()
    }
}
