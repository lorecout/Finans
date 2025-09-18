# Finans - Flutter Financial Management App

## Architecture Overview

**Finans** is a Flutter mobile app for personal financial management with Firebase authentication, local SQLite storage, and Material Design 3 theming.

### Core Components

- **Authentication Flow**: `AuthWrapper` uses `StreamBuilder<User?>` to monitor Firebase auth state changes
- **Data Layer**: Singleton `DatabaseHelper` with specialized helpers (`db_*_helper.dart`) for different entities
- **Navigation**: Named routes in `main.dart` + `BottomNavigationBar` in `HomePage`
- **State Management**: `setState()` with async data loading patterns
- **UI Patterns**: Dynamic theming with `Theme.of(context).colorScheme.*`, gradient backgrounds, card layouts

### Key Files & Patterns

#### Authentication (`lib/widgets/auth_wrapper.dart`)
```dart
// Dual auth support: Firebase + Visitor mode
if ((snapshot.hasData && snapshot.data != null) || _isVisitor) {
  return HomePage(isVisitor: _isVisitor, ...);
}
```

#### Database Pattern (`lib/db_helper.dart`)
```dart
// Singleton with async initialization
static final DatabaseHelper _instance = DatabaseHelper._internal();
Future<Database> get db async {
  if (_db != null) return _db!;
  _db = await _initDb();
  return _db!;
}
```

#### UI Theming (`lib/main.dart`)
```dart
// Material Design 3 with dynamic colors
theme: ThemeData(
  useMaterial3: true,
  colorSchemeSeed: const Color(0xFF0EA5A5),
  brightness: Brightness.light,
),
```

#### Data Loading Pattern (`lib/dashboard_page.dart`)
```dart
@override
void initState() {
  super.initState();
  carregarDados(); // Async data loading
  _carregarOrcamentos();
  _carregarCategorias();
}
```

## Development Workflows

### Build & Run
```bash
flutter pub get
flutter run --debug  # Development
flutter build apk --release  # Production
```

### Database Schema
- `gastos` table: expenses with categories
- `orcamentos` table: budgets by category
- `assinatura` table: premium subscription status
- Version-controlled migrations in `_initDb()`

### Firebase Integration
- Centralized config in `lib/services/firebase_config.dart`
- Google Sign-In with web client ID configuration
- Auth state monitoring via `FirebaseAuth.instance.authStateChanges()`

### Ads & Monetization
- AdMob integration via `lib/services/ads_service.dart`
- In-app purchases via `lib/services/iap_service.dart`
- Premium status managed by `AssinaturaHelper`

## Code Conventions

### Naming Patterns
- Portuguese method names: `carregarDados()`, `salvarGasto()`
- English class names: `DatabaseHelper`, `AuthWrapper`
- File naming: `snake_case` for utilities, `PascalCase` for widgets

### Error Handling
```dart
try {
  await FirebaseAuth.instance.signInWithCredential(credential);
} on FirebaseAuthException catch (e) {
  // Specific error handling with user-friendly messages
  switch (e.code) { ... }
}
```

### Async Patterns
```dart
Future<void> _loadData() async {
  final data = await DatabaseHelper().getGastos();
  if (mounted) setState(() => gastos = data);
}
```

## Integration Points

### External Services
- **Firebase Auth**: User authentication with Google Sign-In
- **Google Mobile Ads**: Banner ads with premium bypass
- **In-App Purchase**: Subscription management
- **Local Notifications**: Scheduled reminders
- **PDF Generation**: Expense reports export

### Cross-Component Communication
- `SharedPreferences` for visitor mode persistence
- `StreamBuilder` for auth state changes
- `ValueNotifier` for premium status updates
- Callback patterns for theme changes

## Common Tasks

### Adding New Database Entity
1. Create `db_[entity]_helper.dart` with CRUD operations
2. Add table creation in `DatabaseHelper._initDb()`
3. Update database version
4. Create corresponding page in `lib/pages/`

### Adding Navigation Page
1. Create page in `lib/pages/[page]_page.dart`
2. Add to `HomePage._pages` list
3. Update `BottomNav` items if needed
4. Add named route in `main.dart` if standalone

### Adding Firebase Feature
1. Configure in `firebase_config.dart`
2. Add to appropriate service file
3. Handle auth state in `AuthWrapper`
4. Update UI based on auth status

## Testing Patterns

### Widget Testing
```dart
testWidgets('Dashboard loads data', (tester) async {
  await tester.pumpWidget(const MaterialApp(home: DashboardPage()));
  await tester.pumpAndSettle(); // Wait for async operations
  expect(find.text('Saldo'), findsOneWidget);
});
```

### Integration Testing
- Test auth flows with Firebase emulators
- Test database operations with in-memory SQLite
- Test IAP flows with StoreKit/TestFlight</content>
<parameter name="filePath">c:\Users\lore-\AndroidStudioProjects\Finans\.github\copilot-instructions.md