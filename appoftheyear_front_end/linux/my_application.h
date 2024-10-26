#ifndef FLUTTER_MY_APPLICATION_H_
#define FLUTTER_MY_APPLICATION_H_

/**
 * my_application_new:
 *
 * Creates a new Flutter-based application.
 *
 * Returns: a new #MyApplication.
 */

#endif  // FLUTTER_MY_APPLICATION_H_
#include "my_application.h"

struct _MyApplication {
    GtkApplication parent_instance;
};

G_DEFINE_TYPE(MyApplication, my_application, GTK_TYPE_APPLICATION)

static void my_application_activate(GApplication *app) {
    // This function is called when the application is activated
    GtkApplication *application = GTK_APPLICATION(app);
    GtkWindow *window = GTK_WINDOW(gtk_application_window_new(application));
    
    // Set the window title
    gtk_window_set_title(window, "My Flutter Application");
    
    // Set the default size of the window
    gtk_window_set_default_size(window, 800, 600);
    
    // Show the window
    gtk_widget_show_all(GTK_WIDGET(window));
}

static void my_application_init(MyApplication *app) {
    // Initialization code can go here
}

MyApplication* my_application_new() {
    return g_object_new(MY_TYPE_APPLICATION, "application-id", "com.example.myapp", NULL);
}

