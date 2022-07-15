// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum Strings {

  internal enum Navigation {
    internal enum Title {
      /// Home
      internal static let home = Strings.tr("Localizable", "Navigation.title.home")
      /// Result
      internal static let result = Strings.tr("Localizable", "Navigation.title.result")
    }
  }

  internal enum Person {
    /// Email
    internal static let email = Strings.tr("Localizable", "Person.email")
  }

  internal enum Request {
    internal enum Person {
      /// Enter your email address
      internal static let email = Strings.tr("Localizable", "Request.person.email")
    }
  }

  internal enum Result {
    internal enum Email {
      /// This email has the correct format.
      internal static let correct = Strings.tr("Localizable", "Result.email.correct")
      /// This email has the wrong format.
      internal static let wrong = Strings.tr("Localizable", "Result.email.wrong")
    }
  }

  internal enum Utils {
    /// Verify
    internal static let verify = Strings.tr("Localizable", "Utils.verify")
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
