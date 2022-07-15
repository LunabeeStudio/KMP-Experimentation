//
//  networkApp.swift
//  Shared
//
//  Created by Anthony Couhier on 20/04/2022.
//

import SwiftUI
import shared

@main
struct networkApp: App {
    
    init() {
        KoinKt.doInitKoin(enableNetworkLogs: false)
    }
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
        }
    }
}
