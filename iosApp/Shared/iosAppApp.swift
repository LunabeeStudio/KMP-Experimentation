//
//  iosAppApp.swift
//  Shared
//
//  Created by Anthony Couhier on 12/05/2022.
//

import SwiftUI
import shared

@main
struct iosAppApp: App {
    
    init() {
        KoinKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ShowNoteScreen()
        }
    }
}
