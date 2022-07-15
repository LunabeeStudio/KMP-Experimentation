//
//  ViewModel.swift
//  iosApp (iOS)
//
//  Created by Anthony Couhier on 12/05/2022.
//

import Foundation
import shared
import Combine
import KMPNativeCoroutinesRxSwift

class ShowNoteViewModel: ObservableObject {
    @Published var notes = [Note]()
    
    let noteRepository: NoteRepository
    
    init(repository: NoteRepository) {
        self.noteRepository = repository
    }
    
    func startObservingPeople() {
        let observable = createObservable(for: noteRepository.getNotesNative())
        
        _ = observable.subscribe(onNext: { value in
            Task { @MainActor in
                self.notes = value
            }
        })
    }
}
