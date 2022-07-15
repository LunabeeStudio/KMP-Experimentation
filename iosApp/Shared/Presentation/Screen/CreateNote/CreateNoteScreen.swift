//
//  CreateNoteScreen.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 12/05/2022.
//

import SwiftUI
import shared

struct CreateNoteScreen: View {
    @StateObject var viewModel = ShowNoteViewModel(repository: KoinHelper().noteRepositoryImpl)
    
    @State private var title: String = ""
    @State private var content: String = ""
    @State private var showAlert = false
    
    var body: some View {
        VStack {
            TextField(
                Strings.Screen.CreateNote.titleField,
                text: $title
            )
            .textInputAutocapitalization(.sentences)
            .border(.secondary)
            
            TextField(
                Strings.Screen.CreateNote.contentField,
                text: $content
            )
            .frame(height: 200, alignment: .topLeading)
            .textInputAutocapitalization(.sentences)
            .border(.secondary)
            
            Button(action: { addItem() }) {
                Text(Strings.Screen.CreateNote.createNoteButton)
            }
            .alert(isPresented: $showAlert) {
                Alert(
                    title: Text(Strings.Screen.CreateNote.Error.EmptyContent.title),
                    message: Text(Strings.Screen.CreateNote.Error.EmptyContent.message)
                )
            }
        }
        .padding()
        .navigationTitle(Strings.Screen.CreateNote.title)
    }
    
    private func addItem() {
        if (title.isEmpty || content.isEmpty) {
            showAlert = true
            return
        }
        viewModel.noteRepository.insertNote(note: Note(id: UUID().uuidString, title: title, content: content))
        title = ""
        content = ""
    }
}

struct CreateNoteScreen_Previews: PreviewProvider {
    static var previews: some View {
        CreateNoteScreen()
    }
}
