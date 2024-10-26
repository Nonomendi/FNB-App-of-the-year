#include <flutter/dart_project.h>
#include <flutter/flutter_view_controller.h>
#include <windows.h>

#include "flutter_window.h"
#include "utils.h"

// Entry point for the application
int APIENTRY wWinMain(_In_ HINSTANCE instance, _In_opt_ HINSTANCE prev,
                      _In_ wchar_t *command_line, _In_ int show_command) {
  // Attach to console when present (e.g., 'flutter run') or create a new console when running with a debugger.
  if (!::AttachConsole(ATTACH_PARENT_PROCESS) && ::IsDebuggerPresent()) {
    CreateAndAttachConsole();
  }

  // Initialize COM
  if (FAILED(::CoInitializeEx(nullptr, COINIT_APARTMENTTHREADED))) {
    return EXIT_FAILURE; // Handle COM initialization error
  }

  // Create a Dart project
  flutter::DartProject project(L"data");

  // Get command line arguments and set them
  std::vector<std::string> command_line_arguments = GetCommandLineArguments();
  project.set_dart_entrypoint_arguments(std::move(command_line_arguments));

  // Create the Flutter window
  FlutterWindow window(project);
  Win32Window::Point origin(10, 10);
  Win32Window::Size size(1280, 720);
  if (!window.Create(L"appoftheyear_front_end", origin, size)) {
    return EXIT_FAILURE; // Handle window creation failure
  }
  window.SetQuitOnClose(true);

  // Message loop
  ::MSG msg;
  while (::GetMessage(&msg, nullptr, 0, 0)) {
    ::TranslateMessage(&msg);
    ::DispatchMessage(&msg);
  }

  // Uninitialize COM before exiting
  ::CoUninitialize();
  return EXIT_SUCCESS;
}
