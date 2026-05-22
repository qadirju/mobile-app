# Task Manager Android Application

**Course:** Mobile Application Development (MAD)  
**Supervisor:** Sir Nisar Ahmed  
**Author:** Ghulam Qadir  
**Date:** May 2026

---

## Abstract

This report presents a Task Manager Android application developed using Kotlin, Jetpack Compose, and Material 3. The application implements the MVVM architectural pattern with reactive state management using StateFlow and Coroutines. Key features include task creation/editing/deletion, real-time search, multi-criteria filtering, priority-based sorting, and progress analytics. The application manages all data in-memory without external database dependencies. Testing validates all core functionality with 100% pass rate across 16 manual test cases.

---

## I. Introduction and Objectives

The Task Manager application addresses the need for efficient personal task organization and management. Objectives include:

1. Implement complete task management system using modern Android frameworks
2. Apply MVVM architecture with reactive state management
3. Provide intuitive UI following Material 3 design standards
4. Enable multi-dimensional filtering, searching, and sorting
5. Support task completion tracking and progress analytics

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

    The application follows the MVVM (Model-View-ViewModel) architecture pattern, which represents the industry standard recommended by Google for Android development. This three-layer architectural approach ensures clean code organization, maintainability, and testability.

    ### Architecture Layers

    | **Layer** | **Responsibility** |
    |---|---|
    | Model | Data definitions and structure (Task data model and Priority classification) |
    | ViewModel | Business logic implementation, state management, filtering and sorting operations |
    | UI (View) | Composable screens and reusable components with declarative interface |
    | Navigation | Multi-screen navigation management and routing |
    | Utilities | Date formatting, constants, and helper functions |

    ### Data Flow Principle

    The application operates on a reactive data flow model. When a user interacts with the interface, the action is transmitted to the ViewModel layer. The ViewModel processes the action and updates the underlying state. These state changes are automatically propagated to the UI layer through reactive streams, triggering appropriate interface updates. This reactive pattern ensures consistency between data and presentation at all times.

    ---

    ## 5. Technology Stack

    The application is built using modern Android development technologies and frameworks selected for their reliability, performance, and industry adoption:

    | **Technology** | **Purpose** |
    |---|---|
    | Kotlin | Primary programming language for type-safe development |
    | Jetpack Compose | Declarative UI framework for modern interface development |
    | Material 3 | Industry-standard design system and component library |
    | Navigation Compose | Multi-screen navigation and routing management |
    | Lifecycle ViewModel | State management with lifecycle awareness |
    | Kotlin Coroutines & StateFlow | Asynchronous operations and reactive state management |
    | Android Gradle | Build system and project management |
    | Android 7.0+ | Minimum supported platform version |

    ### Key Framework Capabilities

    The selected technology stack provides several critical capabilities including smooth list animations with automatic item transitions, swipe gesture handling for delete operations, date and time selection through native dialogs, modal bottom sheet implementations for filter interfaces, keyboard-accessible dropdown menus for task categorization, and animated visibility transitions for the search interface. Additionally, the framework supports theme variations for optimal user experience in both light and dark environments.

    ---

    ## 6. Project Structure

    The application is organized into logical modules following the MVVM architectural pattern:

    - **Main Application Entry Point** — Initializes the application and sets up the root navigation structure
    - **Model Module** — Contains the Task data structure definition and Priority classification enumeration
    - **ViewModel Module** — Implements the TaskViewModel containing all business logic, state management, filtering, sorting, and CRUD operations
    - **Navigation Module** — Defines application routes and manages screen transitions
    - **Screens Module** — Implements three primary user interfaces: Home (task listing), Add/Edit (task form), and Detail (task information view)
    - **Components Module** — Contains reusable UI elements including task cards with action capabilities, priority selection controls, and empty state illustrations
    - **Theme Module** — Defines color schemes, typography guidelines, and visual styling for light and dark modes
    - **Utilities Module** — Provides date formatting functions, overdue detection logic, and application-wide constants

    ---

    ## 7. Data Model

    ### Task Structure

    The Task model is the fundamental data entity in the application. Each task record contains the following attributes:

    - **Unique Identifier** — UUID-based identifier ensuring uniqueness across all tasks
    - **Title** — Required field containing the task name
    - **Description** — Optional detailed information about the task
    - **Completion Status** — Boolean flag indicating whether the task has been marked complete
    - **Priority Level** — Classification as High, Medium, or Low importance
    - **Creation Timestamp** — Unix timestamp recording when the task was created
    - **Due Date** — Optional date field specifying task deadline
    - **Due Time** — Optional time value in standard format (e.g., 09:00 AM)
    - **Category** — Task classification for organizational purposes (Work, Personal, Study, etc.)
    - **Image Attachment** — Optional image URI associated with the task
    - **Reminder Flag** — Boolean indicating reminder activation status
    - **Reminder Timestamp** — Optional timestamp for reminder scheduling

    ### Priority Classification

    The application employs three priority levels ordered by urgency:

    | **Priority Level** | **Visual Color** | **Usage** |
    |---|---|---|
    | High | Red | Urgent and critical tasks |
    | Medium | Amber/Orange | Standard importance tasks |
    | Low | Green | Non-urgent, optional tasks |

    ### Due Date Status Indicators

    Tasks display color-coded status based on their due date and completion state:

    | **Status** | **Visual Color** | **Condition** |
    |---|---|---|
    | Overdue | Deep Red | Past due date and not completed |
    | Due Soon | Orange | Expected within two days |
    | Completed | Green | Task marked as complete |

    ### Task Categories

    The application provides predefined categories for task organization: General, Work, Personal, Study, Health, Shopping, and Finance. This categorization enables effective task grouping and filtering capabilities.

    ---

    ## 8. Features & Functionality

    ### Core Features

    #### 1. Create Task
    - Comprehensive form interface for new task entry
    - Required title field with validation
    - Optional description field for detailed information
    - Priority level selection from three categories
    - Category selection from predefined options
    - Due date selection through calendar interface
    - Due time input through time picker interface
    - Optional reminder flag activation
    - Form validation preventing incomplete submissions

    #### 2. Edit / Update Task
    - Pre-populated form with existing task information
    - All fields available for modification
    - Task creation date preserved during updates
    - Changes saved to the same task record
    - Return to previous screen after successful update

    #### 3. Delete Task
    - Accessible through swipe gesture from task card edge
    - Reveals delete action on screen
    - Optional confirmation dialog on detail screen
    - Animated removal from task list upon completion

    #### 4. Real-Time Search

    The application provides a search interface that can be toggled from the top navigation bar. The search function filters tasks across multiple fields including title, description, and category simultaneously. Search results update instantly as the user types, providing immediate feedback. The search interface expands and collapses with smooth animations for improved usability.

    #### 5. Multi-Dimensional Filtering

    Tasks can be filtered according to multiple criteria simultaneously:

    - **Priority Level** — Display all tasks or filter to show only High, Medium, or Low priority tasks
    - **Category** — Filter by specific categories or view all categories
    - **Completion Status** — Toggle visibility of completed versus pending tasks
    - **Filter Display** — Active filters are displayed as dismissible chips for clear visibility
    - **State Persistence** — Filter selections are maintained during screen navigation

    #### 6. Smart Sorting

    Tasks can be sorted according to four different criteria accessible from the top app bar:

    - Date Created (newest first)
    - Due Date (earliest due first)
    - Priority Level (high to low)
    - Title (alphabetical order)

    #### 7. Task Completion Toggle

    Tasks can be marked as complete through two methods:

    - Inline checkbox on each task card for quick status changes
    - Dedicated button on the task detail screen for prominent completion action
    - Visual feedback includes strikethrough text effect and reduced opacity

    #### 8. Progress Analytics

    The home screen displays a statistics card showing real-time task progress metrics:

    - Total number of tasks
    - Number of pending (incomplete) tasks
    - Number of completed tasks
    - Animated progress bar showing completion percentage

    #### 9. Reminder Indicator

    Tasks support optional reminder functionality:

    - Bell icon displayed on task cards when a reminder is active
    - Toggle control in the task creation and editing form
    - Reminder state is tracked but displayed through UI indicators

    #### 10. Sample Data

    The application includes six pre-loaded sample tasks across multiple categories and priority levels, demonstrating all features on first launch.

    ### Advanced Features

    | **Feature** | **Capability** |
    |---|---|
    | Swipe Gestures | Horizontal swipe on task cards to delete tasks |
    | List Animations | Smooth animated transitions when adding or removing tasks |
    | Search Animation | Expand/collapse animation when opening search interface |
    | Completion Animation | Opacity and color transitions on task completion |
    | Filter Interface | Bottom sheet modal for managing filter selections |
    | Category Selection | Quick-select interface for common task categories |

    ---

    ## 9. Screen-by-Screen Description

    ### Screen 1: Home Screen (Task List)

    The home screen serves as the primary user interface providing a comprehensive overview of all tasks in the system.

    **Key Components:**

    - **Top Navigation Bar** — Displays application title with current task statistics (pending and completed counts), search activation button, filter status indicator, and sort options dropdown
    - **Search Bar** — Animated interface for real-time task searching across title, description, and category fields
    - **Active Filter Indicators** — Visual display of currently applied filters with dismissible chip controls
    - **Statistics Panel** — Summary card displaying total tasks, pending count, completed count, and visual progress indicator
    - **Task List** — Scrollable collection of task cards with smooth animations for additions and removals
    - **Task Cards** — Each card displays priority color indicator, task title with visual completion state, description summary, category label, due date with status coloring, reminder indicator, and completion checkbox
    - **Empty State** — Illustrated placeholder shown when no tasks match current filters or no tasks exist
    - **Add Task Button** — Fixed floating action button for creating new tasks

    **Available Interactions:**

    - Tap task card to view detailed information
    - Swipe card from right to delete task
    - Toggle checkbox to mark task complete
    - Press add button to create new task
    - Activate search to find tasks by keyword
    - Apply filters to customize task display

    ---

    ### Screen 2: Add / Edit Task Screen

    A comprehensive form interface for creating new tasks or modifying existing task information.

    **Form Fields:**

    | **Field** | **Input Type** | **Details** |
    |---|---|---|
    | Task Title | Text input | Required field; validation prevents empty submission |
    | Description | Multi-line text | Optional detailed task information |
    | Priority | Selection control | Choose from High, Medium, or Low |
    | Category | Dropdown menu | Select from predefined categories or view all |
    | Due Date | Date picker | Opens calendar interface for date selection |
    | Due Time | Time picker | Opens time selection interface |
    | Reminder | Toggle switch | Enable or disable reminder notification |

    **Action Buttons:**

    - **Save** — Located in top-right and bottom area; validates all required fields and persists changes
    - **Cancel** — Located in top-left and bottom area; discards changes and returns to previous screen

    ---

    ### Screen 3: Task Detail Screen

    A read-only comprehensive view displaying all task information with action capabilities.

    **Content Sections:**

    - **Completion Status Banner** — Green banner displayed when task is marked complete
    - **Title Section** — Large prominent title display with priority color indicator and priority level badge
    - **Description Area** — Full task description text (if provided)
    - **Metadata Panel** — Complete task information including category, due date and time, reminder status, creation timestamp, and overdue status indicator if applicable
    - **Action Buttons** — Delete button (outlined, red) and Edit button (filled) for task modifications
    - **Completion Control** — Floating action button with visual toggle state (checked/unchecked) for marking task complete

    ---
    - **Description Card** — Full description text (if present)
    - **Metadata Card** — Category, due date, due time, reminder status, creation date
    - **Overdue Badge** — Shown inside metadata when task is past due and incomplete
    - **Action Buttons** — "Delete" (outlined, red) and "Edit" (filled tonal)
    - **FAB** — Toggle completion (green check / grey unchecked)

    ---

    ## 10. State Management

    ### ViewModel State Architecture

    The TaskViewModel implements centralized state management using reactive streams. The application maintains the following state collections:

    - **All Tasks Collection** — Source of truth containing all task records
    - **Filter State** — Encapsulates search query, priority filters, category filters, completion status visibility, and sort preferences
    - **Filtered Tasks Stream** — Derived state automatically computed from tasks and filter state
    - **Statistics Streams** — Real-time computation of total count, completed count, and pending count

    ### Filter State Components

    The filter state captures:

    - Search Query Text — Text string for real-time task searching
    - Priority Filter — Optional priority level selection (High, Medium, Low, or All)
    - Category Filter — Optional category selection or All categories
    - Completion Visibility Flag — Boolean controlling whether to display completed tasks
    - Sort Option — Active sorting criterion

    ### Reactive Pipeline

    The application implements a reactive filtering pipeline where changes to either the task collection or filter state automatically trigger recomputation of the filtered task list. This ensures the user interface always displays current data reflecting all active filters and search criteria. The reactive stream uses an efficient subscription lifecycle that suspends collection when the application moves to the background, optimizing resource consumption.

    ### Available Sorting Options

    Tasks can be sorted according to these criteria:

    | **Sort Criterion** | **Order** | **Primary Use** |
    |---|---|---|
    | Date Created | Newest first | Recent task tracking |
    | Due Date | Earliest due first | Deadline-focused view |
    | Priority | High to Low | Urgency-focused view |
    | Title | Alphabetical A–Z | Reference-based lookup |

    ---

    ## 11. UI/UX Design Principles

    ### Material 3 Design System Implementation

    The application implements the Material 3 (Material You) design language throughout all user interfaces. This includes consistent application of color schemes derived from the system palette, typography standards enforced across all text elements, corner rounding specifications for all visual elements, and dynamic color support on compatible Android devices. The design system ensures visual coherence and professional appearance across all screens and interactions.

    ### Visual Priority Communication

    Task priority is communicated to users through multiple complementary visual indicators working in concert:

    - **Color Bar** — Left-aligned colored bar on each task card providing immediate priority identification
    - **Priority Badge** — Color-coded label displaying priority level text
    - **Due Date Coloring** — Task due date displayed in color corresponding to status (overdue, due soon, or on schedule)
    - **Visual Opacity** — Completed tasks rendered at reduced opacity to visually distinguish them from active tasks

    ### Typography and Spacing Standards

    The application maintains consistent visual hierarchy through standardized typography selections:

    | **Element Type** | **Typography Style** | **Weight** |
    |---|---|---|
    | Screen titles | Large heading style | Bold |
    | Card titles | Medium heading style | Semibold |
    | Body content | Standard body style | Normal weight |
    | Labels and chips | Small label style | Medium weight |
    | Statistical numbers | Medium heading style | Bold |

    ### Animation and Interaction Feedback

    Animations are applied thoughtfully to provide clear feedback without distraction:

    - Task completion: Smooth opacity and color transitions indicating status change
    - Search interface: Expand and collapse animations when opening search functionality
    - List management: Animated additions and removals when tasks are created or deleted
    - Gesture feedback: Swipe-to-delete background reveal with smooth transitions

    ### Accessibility and Contrast

    All color selections maintain sufficient contrast ratios for readable presentation against both light and dark background surfaces, ensuring the application meets accessibility standards for users with color perception differences.

    ---

    ## 12. Key Implementation Details

    ### 12.1 Navigation Architecture

    The application implements type-safe navigation routing using sealed class patterns. This approach provides compile-time safety for navigation parameters, eliminating runtime errors from incorrect route strings. A single TaskViewModel instance is instantiated at the navigation graph level and shared across all screens, ensuring consistent state and seamless data persistence during screen transitions.

    ### 12.2 Swipe-to-Delete Interaction

    Swipe-to-delete functionality enables users to remove tasks through a horizontal gesture from the right edge of task cards. When triggered, the card reveals a delete action background with appropriate visual indicators. The deletion is processed immediately upon completion of the swipe gesture, with the task removed from the collection and the list updated with smooth animation.

    ### 12.3 Type System Optimization

    The development process identified that Kotlin's type system requires special handling for nullable delegated properties. The solution involves capturing nullable property values into local constants before conditional evaluation, allowing the compiler to apply type narrowing and eliminate null-related runtime checks. This optimization improves code reliability and maintainability.

    ### 12.4 Date Selection Interface

    Date and time selection utilizes the Material 3 native picker dialogs, providing users with familiar, system-consistent interfaces. The DatePickerDialog component displays a calendar interface allowing single-date selection, while the TimePicker component provides hour and minute input. Both components integrate seamlessly with the task form and update task fields upon confirmation.

    ### 12.5 Overdue Task Detection

    The application implements continuous monitoring of task due dates relative to the current system time. Tasks are flagged as overdue when the due date has passed and the task remains incomplete. This detection is performed in real-time as the application runs, enabling immediate visual feedback through color coding and status indicators.

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

    ### Challenge 1: Type System Constraints on Delegated Properties

    **Problem:** Kotlin's type inference system prevents automatic type narrowing on properties that are backed by delegation mechanisms. This creates situations where the compiler cannot guarantee value stability between null-checking and subsequent usage.

    **Solution:** Implemented a pattern of capturing property values into local constants before conditional evaluation. This allows the compiler to apply standard type narrowing rules, as local constants are guaranteed to be immutable and cannot change between checks and usage.

    ---

    ### Challenge 2: Shared State Across Navigation Destinations

    **Problem:** Each screen destination in a navigation graph typically operates within its own lifecycle scope. Creating ViewModels independently in each destination results in multiple instances with independent state, leading to data inconsistency and synchronization failures across screens.

    **Solution:** Instantiated a single TaskViewModel at the navigation graph level and explicitly passed the same instance to all screen destinations as a constructor parameter. This pattern ensures one source of truth for all application state across all screens and prevents state fragmentation.

    ---

    ### Challenge 3: Complex Multi-Criteria Filtering

    **Problem:** The application requires filtering and sorting based on five independent parameters simultaneously: search text, priority level, category, completion status, and sort order. Managing these as separate state streams would create complex subscription patterns and introduce maintenance difficulties.

    **Solution:** Consolidated all filtering parameters into a single FilterState data class, reducing the reactive pipeline to combine just two flows: the task collection and the filter state object. This approach dramatically simplifies the implementation while maintaining full reactivity to all state changes.

    ---

    ### Challenge 4: Animated List Item Transitions

    **Problem:** The Android framework's animation capabilities evolved between development tool versions. Legacy animation APIs were deprecated and removed in newer versions, requiring code updates to maintain compatibility with current framework releases.

    **Solution:** Updated animation implementations to use the current stable API for item animations within scrollable lists. The new API provides equivalent or superior functionality with better performance characteristics and improved consistency with modern framework design patterns.

    ---

    ## 15. Future Enhancements

    The following features represent potential future development directions and enhancements to expand application capabilities:

    | **Feature** | **Description** | **Priority** |
    |---|---|---|
    | Persistent Data Storage | Local database implementation for data persistence across application sessions | High |
    | Manual Task Reordering | Drag-and-drop interface for custom task arrangement | High |
    | System Notifications | Native push notifications and alarm-based reminders | High |
    | Task Grouping | Organize task list by category or due date with collapsible sections | Medium |
    | Recurring Tasks | Support for daily, weekly, or monthly task recurrence | Medium |
    | Task Subtasks | Create nested checklists within individual tasks | Medium |
    | Cloud Synchronization | Multi-device synchronization through cloud services | Medium |
    | Home Screen Widgets | Quick-view widgets on device home screen | Medium |
    | Data Export | Export task data to standard formats (PDF, CSV) | Low |
    | Theme Customization | User-controlled light/dark mode selection | Low |
    | Haptic Feedback | Vibration effects on user interactions | Low |

    ---

    ## 16. Conclusion

    This project has successfully delivered a comprehensive, production-quality Task Manager application for the Android platform. The application was developed using modern industry-standard technologies and architectural patterns, demonstrating professional-level software engineering practices.

    ### Key Achievements

    The application successfully demonstrates:

    1. **Clean Architectural Design** — Clear separation between data, business logic, and user interface layers following established software engineering principles
    2. **Contemporary Development Practices** — Implementation of current Android development frameworks and reactive programming patterns
    3. **Responsive State Management** — Efficient handling of application state with automatic propagation of changes to the user interface
    4. **Professional User Experience** — Polished interface design incorporating Material 3 standards with smooth animations and intuitive interactions
    5. **Complete Feature Implementation** — All required functionality including task management, search, filtering, sorting, date tracking, and reminder capabilities
    6. **Lightweight Architecture** — Self-contained application without external database or backend dependencies

    ### Project Impact

    This development project has provided valuable practical experience in:

    - Application of modern Android development frameworks
    - Implementation of reactive programming concepts
    - User interface design and interaction patterns
    - State management in complex applications
    - Professional software architecture practices

    The team is now well-prepared to apply these concepts and techniques to real-world professional development projects.

    ---

    ## References

    1. Android Official Documentation — Jetpack Compose Framework Reference
    2. Material 3 Design System — Official Design Guidelines
    3. Kotlin Programming Language — Official Documentation
    4. Android Navigation Framework — Implementation Guide
    5. Reactive Programming with Kotlin — Flow and State Management
    6. Android Architecture Patterns — MVVM Implementation Guide

    ---

    *Report prepared by Ghulam Qadir*

    *Mobile Application Development Project*

    *Supervised by Sir Nisar Ahmed*

    *May 2026*
