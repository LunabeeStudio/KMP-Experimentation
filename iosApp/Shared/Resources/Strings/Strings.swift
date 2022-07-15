// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum Strings {

  internal enum Screen {
    internal enum CreateNote {
      /// Content
      internal static let contentField = Strings.tr("Localizable", "Screen.CreateNote.contentField")
      /// Create notes
      internal static let createNoteButton = Strings.tr("Localizable", "Screen.CreateNote.createNoteButton")
      /// Create notes
      internal static let title = Strings.tr("Localizable", "Screen.CreateNote.title")
      /// Title
      internal static let titleField = Strings.tr("Localizable", "Screen.CreateNote.titleField")
      internal enum Error {
        internal enum EmptyContent {
          /// Verify your title and content, they should not be empty
          internal static let message = Strings.tr("Localizable", "Screen.CreateNote.Error.EmptyContent.message")
          /// Note error
          internal static let title = Strings.tr("Localizable", "Screen.CreateNote.Error.EmptyContent.title")
        }
      }
    }
    internal enum ShowNotes {
      /// Show notes
      internal static let title = Strings.tr("Localizable", "Screen.ShowNotes.title")
    }
  }
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension Strings {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    let format = BundleToken.bundle.localizedString(forKey: key, value: nil, table: table)
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

// swiftlint:disable convenience_type
private final class BundleToken {
  static let bundle: Bundle = {
    #if SWIFT_PACKAGE
    return Bundle.module
    #else
    return Bundle(for: BundleToken.self)
    #endif
  }()
}
// swiftlint:enable convenience_type
