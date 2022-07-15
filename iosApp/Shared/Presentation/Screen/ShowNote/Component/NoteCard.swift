//
//  NoteCard.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 12/05/2022.
//

import SwiftUI
import shared

struct NoteCard: View {    
    let note: Note
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(note.title)
                .font(.title)
            Text(note.content)
                .font(.body)
                .foregroundColor(Color.gray)
                .multilineTextAlignment(.leading)
        }
    }
}
