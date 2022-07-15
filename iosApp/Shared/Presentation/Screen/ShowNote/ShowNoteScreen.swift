//
//  ContentView.swift
//  Shared
//
//  Created by Anthony Couhier on 12/05/2022.
//

import SwiftUI
import shared

struct ShowNoteScreen: View {
    
    @StateObject var viewModel = ShowNoteViewModel(repository: KoinHelper().noteRepositoryImpl)
    
    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.notes, id: \.id) { note in
                    NoteCard(note: note)
                }
                .onDelete(perform: deleteItems)
            }
            .onAppear {
                viewModel.startObservingPeople()
            }
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    EditButton()
                }
                ToolbarItem {
                    NavigationLink(destination: CreateNoteScreen()) {
                        Label("Add note", systemImage: "plus")
                    }
                }
            }
            .navigationTitle(Strings.Screen.ShowNotes.title)
        }
    }

    private func deleteItems(offsets: IndexSet) {
        withAnimation {
            offsets.map { viewModel.notes[$0] }.forEach({ note in
                viewModel.noteRepository.deleteNote(note: note)
            })

        }
    }
}

