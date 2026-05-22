# Task Manager Application
## Mobile Application Development (MAD) — Project Report

---

| Field | Details |
|---|---|
| **Project Title** | Task Manager Android Application |
| **Course** | Mobile Application Development (MAD) |
| **Supervisor** | Sir Nisar Ahmed |
| **Submitted By** | Ghulam Qadir |
| **Technology** | Kotlin · Jetpack Compose · Material 3 |
| **Platform** | Android (Min SDK 24 — Android 7.0+) |
| **Date** | May 2026 |

---

## Table of Contents

1. [Executive Summary](#1-executive-summary)
2. [Introduction](#2-introduction)
3. [Objectives](#3-objectives)
4. [System Architecture](#4-system-architecture)
5. [Technology Stack](#5-technology-stack)
6. [Project Structure](#6-project-structure)
7. [Data Model](#7-data-model)
8. [Features & Functionality](#8-features--functionality)
9. [Screen-by-Screen Description](#9-screen-by-screen-description)
10. [State Management](#10-state-management)
11. [UI/UX Design Principles](#11-uiux-design-principles)
12. [Key Implementation Details](#12-key-implementation-details)
13. [Testing & Validation](#13-testing--validation)
14. [Challenges & Solutions](#14-challenges--solutions)
15. [Future Enhancements](#15-future-enhancements)
16. [Conclusion](#16-conclusion)

---

## 1. Executive Summary

This report presents the design and implementation of a fully functional **Task Manager Android Application** developed as part of the Mobile Application Development (MAD) course under the supervision of **Sir Nisar Ahmed**. The application is built entirely using **Kotlin** and **Jetpack Compose** with **Material 3** design guidelines.

The app enables users to efficiently manage their daily tasks by providing features such as task creation, editing, deletion, searching, filtering, sorting, and completion tracking — all powered by in-memory state management without any external database or backend dependency.

The project demonstrates a production-quality implementation of the **MVVM (Model-View-ViewModel)** architectural pattern, reactive programming with **Kotlin StateFlow**, and modern declarative UI development with **Jetpack Compose**.

---

## 2. Introduction

In the modern fast-paced world, effective task management is essential for productivity. Mobile task manager applications have become one of the most widely used categories of apps, helping individuals and teams organize their work, set priorities, and track progress.

This project builds a comprehensive Task Manager application for Android that follows industry-standard software engineering practices. The application does not rely on any database or cloud backend — all data is managed in memory inside a ViewModel, which satisfies the project's core constraint while still demonstrating robust state management.

### Problem Statement

Many users struggle with organizing their tasks, tracking deadlines, and prioritizing work. The goal of this project is to design a clean, intuitive, and feature-rich mobile application that solves this problem using the latest Android development tools.

### Scope

The application covers the full task lifecycle:
- **Creation** → **Management** → **Completion** → **Deletion**

It includes advanced features such as real-time search, multi-dimensional filtering, priority classification, due date tracking, and reminder indicators.

---

## 3. Objectives

### Primary Objectives

- Implement a complete Task Manager Android application using **Kotlin** and **Jetpack Compose**
- Follow the **MVVM** architectural pattern strictly
- Apply **Material 3** design system for a modern and professional UI
- Manage all application state using **ViewModel** and **StateFlow** — no database required
- Implement **Navigation Compose** for multi-screen navigation

### Secondary Objectives

- Demonstrate real-time search and filtering
- Implement smooth animations and transitions
- Provide an intuitive user experience with clear visual feedback
- Write clean, modular, and maintainable code
- Support both **Light** and **Dark** themes

### Learning Outcomes

Through this project, the team gained hands-on experience with:
- Declarative UI development with Jetpack Compose
- Reactive state management with Kotlin Coroutines and StateFlow
- MVVM architecture in Android
- Material 3 theming and component usage
- Navigation in Compose applications

---

## 4. System Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture, which is the recommended pattern by Google for Android development.

```
┌─────────────────────────────────────────────────────┐
│                     UI Layer                        │
│  ┌──────────┐  ┌──────────┐  ┌──────────────────┐  │
│  │  Home    │  │ AddEdit  │  │   TaskDetail     │  │
│  │  Screen  │  │  Screen  │  │     Screen       │  │
│  └────┬─────┘  └────┬─────┘  └────────┬─────────┘  │
│       └─────────────┴──────────────────┘            │
│                      │                              │
│             ┌─────────▼──────────┐                  │
│             │   Reusable UI      │                  │
│             │   Components       │                  │
│             │  (TaskCard, etc.)  │                  │
│             └─────────┬──────────┘                  │
└───────────────────────┼─────────────────────────────┘
                        │ observes StateFlow
┌───────────────────────▼─────────────────────────────┐
│                  ViewModel Layer                     │
│  ┌───────────────────────────────────────────────┐  │
│  │              TaskViewModel                    │  │
│  │  - filteredTasks: StateFlow<List<Task>>       │  │
│  │  - filterState: StateFlow<FilterState>        │  │
│  │  - completedCount / pendingCount              │  │
│  │  - addTask / updateTask / deleteTask          │  │
│  │  - toggleCompletion / search / filter / sort  │  │
│  └───────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
                        │ reads/writes
┌───────────────────────▼─────────────────────────────┐
│                   Model Layer                        │
│  ┌─────────────┐     ┌────────────────────────────┐ │
│  │  Task.kt    │     │    Priority (enum)          │ │
│  │  data class │     │  HIGH / MEDIUM / LOW        │ │
│  └─────────────┘     └────────────────────────────┘ │
└─────────────────────────────────────────────────────┘
```

### Architecture Layers

| Layer | Responsibility |
|---|---|
| **Model** | Data definitions — `Task` data class, `Priority` enum |
| **ViewModel** | Business logic, state management, filtering, sorting |
| **UI (View)** | Composable screens and components, purely declarative |
| **Navigation** | `NavGraph` using Navigation Compose |
| **Utils** | Date formatting, constants |

### Data Flow

```
User Action (tap/swipe/type)
        ↓
Composable UI calls ViewModel function
        ↓
ViewModel updates MutableStateFlow
        ↓
StateFlow emits new value via combine()
        ↓
Composable recomposes with new state
        ↓
UI reflects the updated data
```

---

## 5. Technology Stack

| Technology | Version | Purpose |
|---|---|---|
| **Kotlin** | 2.0.21 | Primary programming language |
| **Jetpack Compose** | 1.7.2 (BOM 2024.09.00) | Declarative UI framework |
| **Material 3** | 1.3.0 | Design system and components |
| **Navigation Compose** | 2.8.4 | Multi-screen navigation |
| **Lifecycle ViewModel** | 2.10.0 | ViewModel + `viewModelScope` |
| **Lifecycle ViewModel Compose** | 2.10.0 | `viewModel()` Composable integration |
| **Kotlin Coroutines** | (bundled) | Asynchronous state with `StateFlow` |
| **Android Gradle Plugin** | 9.0.1 | Build system |
| **Min SDK** | 24 (Android 7.0) | Minimum device support |
| **Target / Compile SDK** | 36 (Android 15) | Target platform |

### Key Jetpack Compose APIs Used

- `LazyColumn` with `animateItem()` for smooth list animations
- `SwipeToDismissBox` for swipe-to-delete gesture
- `DatePickerDialog` and `DatePicker` for date selection
- `TimePicker` in `AlertDialog` for time selection
- `ModalBottomSheet` for filter panel
- `ExposedDropdownMenuBox` for category selection
- `AnimatedVisibility` for search bar expand/collapse
- `animateColorAsState` and `animateFloatAsState` for completion animations

---

## 6. Project Structure

```
app/src/main/java/com/example/taskmanager/
│
├── MainActivity.kt                  ← App entry point
│
├── model/
│   └── Task.kt                      ← Task data class + Priority enum
│
├── viewmodel/
│   └── TaskViewModel.kt             ← MVVM ViewModel with all business logic
│
├── ui/
│   ├── navigation/
│   │   └── NavGraph.kt              ← Navigation routes and NavHost
│   │
│   ├── screens/
│   │   ├── HomeScreen.kt            ← Task list with search, filter, stats
│   │   ├── AddEditTaskScreen.kt     ← Create / update task form
│   │   └── TaskDetailScreen.kt      ← Full task detail view
│   │
│   ├── components/
│   │   ├── TaskCard.kt              ← Reusable task card with swipe
│   │   ├── PrioritySelector.kt      ← Priority chip group
│   │   └── EmptyStateView.kt        ← Empty list illustration
│   │
│   └── theme/
│       ├── Color.kt                 ← Color palette + priority colors
│       ├── Theme.kt                 ← Light/Dark MaterialTheme
│       └── Type.kt                  ← Typography definitions
│
└── utils/
    ├── DateUtils.kt                 ← Date formatting, overdue detection
    └── Constants.kt                 ← DEFAULT_CATEGORIES list
```

---

## 7. Data Model

### Task Data Class

The `Task` data class is the core model of the application. It captures all meaningful attributes of a task:

```kotlin
data class Task(
    val id: String,               // UUID — unique identifier
    val title: String,            // Required — task name
    val description: String,      // Optional — additional details
    val isCompleted: Boolean,     // Completion status
    val priority: Priority,       // HIGH / MEDIUM / LOW
    val createdAt: Long,          // Unix timestamp of creation
    val dueDate: Long?,           // Optional due date (nullable)
    val dueTime: String?,         // Optional time string e.g. "09:00 AM"
    val category: String,         // General / Work / Personal / etc.
    val imageUri: String?,        // Optional image attachment
    val reminderEnabled: Boolean, // Reminder toggle flag
    val reminderTime: Long?       // Optional reminder timestamp
)
```

### Priority Enum

```kotlin
enum class Priority(val label: String, val order: Int) {
    HIGH("High", 0),
    MEDIUM("Medium", 1),
    LOW("Low", 2)
}
```

Each priority has an `order` integer used for sorting (lower = more urgent).

### Priority Color System

| Priority | Color | Hex Code | Usage |
|---|---|---|---|
| **HIGH** | Red | `#E53935` | Urgent, critical tasks |
| **MEDIUM** | Amber | `#FFA000` | Standard importance |
| **LOW** | Green | `#43A047` | Nice-to-have tasks |

### Due Date Status Colors

| Status | Color | Hex Code | Condition |
|---|---|---|---|
| **Overdue** | Deep Red | `#D32F2F` | Past due date, not completed |
| **Due Soon** | Orange | `#F57C00` | Within 2 days |
| **Completed** | Green | `#388E3C` | Task marked done |

### Available Categories

`General · Work · Personal · Study · Health · Shopping · Finance`

---

## 8. Features & Functionality

### Core Features

#### 1. Create Task
- Full form with title (required), description, priority, category
- Date picker (Material 3 `DatePickerDialog`)
- Time picker (Material 3 `TimePicker`)
- Reminder toggle
- Input validation (title is mandatory)

#### 2. Edit / Update Task
- Pre-populated form with existing task data
- All fields editable
- Saves back to the original task ID preserving creation date

#### 3. Delete Task
- Swipe left on any task card to reveal delete background
- Confirmation dialog on the Detail screen
- Animated removal from the list

#### 4. Real-Time Search
- Search bar toggled from the top app bar
- Filters by title, description, and category simultaneously
- `AnimatedVisibility` expand/collapse animation
- Live results update on every keystroke

#### 5. Multi-Dimensional Filtering
- **By Priority**: All / High / Medium / Low
- **By Category**: All / Work / Personal / Study / etc.
- **By Status**: Show completed / Pending only
- Active filters displayed as dismissible chips
- Filter state persists across navigation

#### 6. Smart Sorting
- Sort by: Date Created, Due Date, Priority, Title (A–Z)
- Controlled from a dropdown in the top app bar

#### 7. Task Completion Toggle
- Checkbox on each card for inline toggling
- FAB on the Detail screen for prominent toggle
- Completion triggers: strikethrough text, reduced opacity, green checkmark

#### 8. Progress Analytics
- Stats card on Home screen showing: Total / Pending / Done counts
- Animated linear progress bar showing completion percentage

#### 9. Reminder Indicator
- Bell icon appears on cards when reminder is active
- Toggle in the Add/Edit form
- No AlarmManager — simulated via UI state flag

#### 10. Sample Data
- 6 pre-loaded tasks across multiple categories and priorities
- Demonstrates all features on first launch

### Advanced Features

| Feature | Implementation |
|---|---|
| Swipe-to-delete | `SwipeToDismissBox` (Material 3) |
| Animated list items | `Modifier.animateItem()` in `LazyColumn` |
| Search bar animation | `AnimatedVisibility` with slide + fade |
| Completion fade | `animateFloatAsState` (alpha 1.0 → 0.65) |
| Completion color | `animateColorAsState` on FAB |
| Filter bottom sheet | `ModalBottomSheet` with smooth drag handle |
| Category quick-select | `LazyRow` of filter chips in form |

---

## 9. Screen-by-Screen Description

### Screen 1: Home Screen (Task List)

The main screen the user sees on launch. It provides a comprehensive overview of all tasks.

**Components:**
- **Top App Bar** — Title with pending/done subtitle, search icon, filter icon (badged when active), sort dropdown
- **Search Bar** — Animated collapse/expand; filters tasks in real time
- **Active Filter Chips** — Scrollable row of dismissible chips for each active filter
- **Stats Card** — Total / Pending / Done counts with animated progress bar
- **LazyColumn** — Scrollable task list with animated item additions/removals
- **Task Cards** — Each card shows: priority color bar, title (with strikethrough if done), description preview, category tag, due date/time with color coding, reminder icon, completion checkbox
- **Empty State** — Illustrated empty view when no tasks match, with optional CTA button
- **Extended FAB** — "New Task" button fixed at bottom right

**Interactions:**
- Tap card → Task Detail Screen
- Swipe left on card → Delete with animation
- Tap checkbox → Toggle completion inline
- Tap FAB → Add/Edit Screen (new task mode)

---

### Screen 2: Add / Edit Task Screen

A scrollable form for creating a new task or editing an existing one.

**Fields:**
| Field | Control | Notes |
|---|---|---|
| Title | `OutlinedTextField` | Required; shows error if blank |
| Description | `OutlinedTextField` | Multi-line (3–5 rows) |
| Priority | `FilterChip` group | High / Medium / Low |
| Category | `ExposedDropdownMenuBox` | Dropdown + quick chip row |
| Due Date | Tappable surface | Opens `DatePickerDialog` |
| Due Time | Tappable surface | Opens M3 `TimePicker` in dialog |
| Reminder | `Switch` | Toggles reminder flag |

**Actions:**
- **Save** (top-right text button or bottom primary button) — validates and saves
- **Cancel** (top-left back arrow or bottom outlined button) — discards changes

---

### Screen 3: Task Detail Screen

A read-only view of a single task with all metadata displayed clearly.

**Sections:**
- **Completion Banner** — Green banner shown when task is completed
- **Title Block** — Priority bar + large title + priority badge
- **Description Card** — Full description text (if present)
- **Metadata Card** — Category, due date, due time, reminder status, creation date
- **Overdue Badge** — Shown inside metadata when task is past due and incomplete
- **Action Buttons** — "Delete" (outlined, red) and "Edit" (filled tonal)
- **FAB** — Toggle completion (green check / grey unchecked)

---

## 10. State Management

### ViewModel State Architecture

The `TaskViewModel` holds all application state using Kotlin `StateFlow`:

```
TaskViewModel
├── _allTasks: MutableStateFlow<List<Task>>    (source of truth)
├── _filterState: MutableStateFlow<FilterState> (search, filters, sort)
│
├── filteredTasks: StateFlow<List<Task>>       ← combine(_allTasks, _filterState)
├── totalCount: StateFlow<Int>                 ← _allTasks.map { it.size }
├── completedCount: StateFlow<Int>             ← _allTasks.map { count(isCompleted) }
└── pendingCount: StateFlow<Int>               ← _allTasks.map { count(!isCompleted) }
```

### FilterState Data Class

```kotlin
data class FilterState(
    val searchQuery: String = "",
    val selectedPriority: Priority? = null,
    val selectedCategory: String? = null,
    val showCompleted: Boolean = true,
    val sortBy: SortOption = SortOption.CREATED_DATE
)
```

### Reactive Pipeline

The `filteredTasks` StateFlow is derived using `combine`, which automatically re-computes whenever either `_allTasks` or `_filterState` changes:

```kotlin
val filteredTasks = combine(_allTasks, _filterState) { tasks, filters ->
    tasks
        .filter { matchesSearch && matchesPriority && matchesCategory && matchesCompletion }
        .sortedWith(sortComparator)
}.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000L),
    initialValue = ...
)
```

`SharingStarted.WhileSubscribed(5_000L)` ensures the flow stops collecting 5 seconds after all subscribers disappear (e.g., when the app goes to the background), which is a best-practice for resource efficiency.

### Sort Options

```kotlin
enum class SortOption(val label: String) {
    CREATED_DATE("Date Created"),   // Newest first (default)
    DUE_DATE("Due Date"),           // Earliest due first
    PRIORITY("Priority"),           // High → Medium → Low
    TITLE("Title")                  // Alphabetical A–Z
}
```

---

## 11. UI/UX Design Principles

### Material 3 Design System

The application uses **Material 3** (Material You) throughout:
- `MaterialTheme.colorScheme` for all color references
- `MaterialTheme.typography` for consistent text styles
- `MaterialTheme.shapes` for corner rounding
- Dynamic color support on Android 12+ (`dynamicLightColorScheme` / `dynamicDarkColorScheme`)

### Visual Hierarchy

Each task card communicates priority at a glance through:
1. **Left color bar** — Immediately visible priority indicator
2. **Priority badge** — Color-coded label (High/Medium/Low)
3. **Due date color** — Red for overdue, orange for due soon
4. **Alpha reduction** — Completed tasks rendered at 65% opacity

### Spacing & Typography

| Element | Style | Weight |
|---|---|---|
| Screen titles | `titleLarge` | Bold |
| Card titles | `titleMedium` | SemiBold |
| Body text | `bodyMedium` / `bodySmall` | Normal |
| Chips & labels | `labelSmall` / `labelMedium` | Medium |
| Stats numbers | `headlineMedium` | Bold |

### Animations

| Interaction | Animation |
|---|---|
| Task completion toggle | `animateFloatAsState` (opacity) |
| FAB completion color | `animateColorAsState` |
| Search bar open/close | `AnimatedVisibility` (expand + fade) |
| List item add/remove | `animateItem()` in LazyColumn |
| Swipe-to-delete background | `SwipeToDismissBox` built-in |

### Color Accessibility

All priority and status colors are chosen to maintain readable contrast ratios against both light and dark surface backgrounds, with the option for dark-theme-specific container variants (`PriorityHighContainerDark`, etc.).

---

## 12. Key Implementation Details

### 12.1 Navigation Setup

Navigation uses the **type-safe route pattern** with sealed classes:

```kotlin
sealed class Screen(val route: String) {
    data object Home   : Screen("home")
    data object AddEdit: Screen("add_edit/{taskId}") {
        fun createRoute(taskId: String = "new") = "add_edit/$taskId"
    }
    data object Detail : Screen("detail/{taskId}") {
        fun createRoute(taskId: String) = "detail/$taskId"
    }
}
```

A single `TaskViewModel` instance is shared across all screens via `viewModel()` scoped to the `NavHost`, ensuring consistent state.

### 12.2 Swipe-to-Delete

```kotlin
SwipeToDismissBox(
    state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete(); true
            } else false
        },
        positionalThreshold = { it * 0.4f }
    ),
    enableDismissFromEndToStart = true,
    backgroundContent = { DeleteBackground() }
) { /* TaskCard content */ }
```

### 12.3 Smart Cast Fix

Kotlin's smart cast does not apply to delegated properties (e.g., `var` backed by `mutableStateOf`). This was resolved by capturing nullable fields into local `val`s before conditional use:

```kotlin
val taskDueDate = localTask.dueDate   // local val — smart cast works
if (taskDueDate != null) {
    DateUtils.formatFullDate(taskDueDate)  // ✓ compiles
}
```

### 12.4 Date Picker Integration

```kotlin
val datePickerState = rememberDatePickerState(
    initialSelectedDateMillis = dueDate ?: System.currentTimeMillis()
)
DatePickerDialog(
    onDismissRequest = { showDatePicker = false },
    confirmButton = {
        TextButton(onClick = {
            dueDate = datePickerState.selectedDateMillis
            showDatePicker = false
        }) { Text("OK") }
    }
) { DatePicker(state = datePickerState) }
```

### 12.5 Overdue Detection (DateUtils)

```kotlin
fun isOverdue(dueDate: Long?): Boolean =
    dueDate != null && dueDate < System.currentTimeMillis()

fun isDueSoon(dueDate: Long?): Boolean {
    if (dueDate == null) return false
    val twoDays = System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000L
    return dueDate in System.currentTimeMillis()..twoDays
}
```

---

## 13. Testing & Validation

### Manual Test Cases

| Test Case | Expected Result | Status |
|---|---|---|
| Add a task with empty title | Validation error shown | ✅ Pass |
| Add a task with all fields | Task appears in list | ✅ Pass |
| Toggle task completion | Checkbox updates, alpha changes | ✅ Pass |
| Swipe task card left | Task deleted with animation | ✅ Pass |
| Search by title keyword | Only matching tasks shown | ✅ Pass |
| Search by category name | Category-matched tasks shown | ✅ Pass |
| Filter by HIGH priority | Only HIGH tasks displayed | ✅ Pass |
| Filter pending only | Completed tasks hidden | ✅ Pass |
| Sort by Due Date | Tasks ordered by soonest due | ✅ Pass |
| Sort by Priority | HIGH → MEDIUM → LOW order | ✅ Pass |
| Clear all filters | Full list restored | ✅ Pass |
| Edit task fields | Updated values saved | ✅ Pass |
| Delete from detail screen | Confirmation dialog, then removed | ✅ Pass |
| Navigate back from detail | Returns to list | ✅ Pass |
| Empty state (no tasks) | Illustrated empty view shown | ✅ Pass |
| Empty state (filtered) | "No results" message shown | ✅ Pass |

### Build Verification

The project compiles successfully with:
- Android Gradle Plugin 9.0.1
- Kotlin 2.0.21
- Compose BOM 2024.09.00
- Navigation Compose 2.8.4

---

## 14. Challenges & Solutions

### Challenge 1: Smart Cast on Delegated Properties

**Problem:** Kotlin's type system does not allow smart casts on `var` properties backed by `mutableStateOf` because the compiler cannot guarantee the value hasn't changed between the null-check and the use site.

**Error:**
```
Smart cast to 'Long' is impossible, because 'dueDate' is a delegated property
```

**Solution:** Capture the property into a local `val` before the null check:
```kotlin
val taskDueDate = localTask.dueDate
if (taskDueDate != null) {
    DateUtils.formatFullDate(taskDueDate)  // Smart cast works on local val
}
```

---

### Challenge 2: Sharing ViewModel Across Screens

**Problem:** Each composable destination in `NavHost` has its own lifecycle. If `viewModel()` is called inside each screen separately, each screen gets a different ViewModel instance, causing inconsistent state.

**Solution:** Create a single `TaskViewModel` instance inside `TaskNavGraph` and pass it down to all screens as a parameter:
```kotlin
@Composable
fun TaskNavGraph(navController: NavHostController) {
    val viewModel: TaskViewModel = viewModel()  // Single instance
    NavHost(...) {
        composable("home") { HomeScreen(viewModel = viewModel, ...) }
        composable("add_edit/{id}") { AddEditTaskScreen(viewModel = viewModel, ...) }
        composable("detail/{id}") { TaskDetailScreen(viewModel = viewModel, ...) }
    }
}
```

---

### Challenge 3: Combining Multiple StateFlows Efficiently

**Problem:** The application needs to filter and sort tasks based on five different parameters simultaneously (search query, priority, category, completion filter, sort option). Managing these as separate StateFlows while keeping the derived `filteredTasks` flow reactive required careful design.

**Solution:** Grouped all filter parameters into a single `FilterState` data class, allowing a clean `combine` of just two flows — `_allTasks` and `_filterState`:
```kotlin
val filteredTasks = combine(_allTasks, _filterState) { tasks, filters ->
    applyFiltersAndSort(tasks, filters)
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), ...)
```

This avoids the complexity of nested `combine` calls and keeps the code readable.

---

### Challenge 4: Animated List Items

**Problem:** Jetpack Compose's `LazyColumn` API changed between versions. The legacy `animateItemPlacement()` modifier was removed in Compose 1.7.x.

**Solution:** Used the updated `animateItem()` API available in Compose 1.7.x (Compose BOM 2024.09.00):
```kotlin
items(items = tasks, key = { it.id }) { task ->
    TaskCard(
        task = task,
        modifier = Modifier.animateItem(
            fadeInSpec = tween(200),
            fadeOutSpec = tween(200),
            placementSpec = tween(300)
        )
    )
}
```

---

## 15. Future Enhancements

The following features are planned or recommended for future development:

| Feature | Description | Priority |
|---|---|---|
| **Persistent Storage** | Room database for data survival across app restarts | High |
| **Drag & Drop Reorder** | `ReorderableList` to manually reorder tasks | High |
| **Real Notifications** | `AlarmManager` + `NotificationManager` for actual reminders | High |
| **Task Grouping** | Group tasks by category or due date in the list | Medium |
| **Task Recurrence** | Daily / weekly repeating tasks | Medium |
| **Subtasks / Checklist** | Nested checklist items within a task | Medium |
| **Cloud Sync** | Firebase Firestore for cross-device sync | Medium |
| **Widgets** | Home screen widget showing pending tasks | Medium |
| **Export** | Export tasks as PDF or CSV | Low |
| **Dark/Light toggle** | Manual theme override in settings | Low |
| **Haptic feedback** | Vibration on swipe-delete and completion | Low |

---

## 16. Conclusion

This project successfully delivers a **production-quality Task Manager Android application** developed entirely with **Kotlin**, **Jetpack Compose**, and **Material 3**. The application demonstrates:

1. **Clean MVVM Architecture** — Clear separation of concerns between data, logic, and UI layers
2. **Modern Android Development** — Full use of Jetpack Compose, Navigation Compose, StateFlow, and Coroutines
3. **Reactive State Management** — Tasks filtered and sorted reactively using `combine` and `stateIn`
4. **Professional UI/UX** — Material 3 design, smooth animations, priority color coding, and an intuitive layout
5. **Feature Completeness** — All required features (CRUD, search, filter, sort, date/time, reminder) implemented
6. **No External Dependencies** — No database, no backend — purely in-memory state as required

The project has been an excellent practical exercise in applying modern Android development concepts and has prepared the team for building real-world applications at a professional level.

---

## References

1. Android Developers — Jetpack Compose Documentation: https://developer.android.com/compose
2. Material 3 Design Guidelines: https://m3.material.io
3. Kotlin Documentation: https://kotlinlang.org/docs
4. Navigation Compose: https://developer.android.com/guide/navigation/design/nav-compose
5. Kotlin Coroutines & Flow: https://kotlinlang.org/docs/flow.html
6. Android Architecture Guides (MVVM): https://developer.android.com/topic/architecture
7. Compose BOM Release Notes: https://developer.android.com/jetpack/compose/bom/bom-mapping

---

*Report prepared by Ghulam Qadir — MAD Project, supervised by Sir Nisar Ahmed.*
