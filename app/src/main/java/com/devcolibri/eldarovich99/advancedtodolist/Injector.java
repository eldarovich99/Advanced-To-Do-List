package com.devcolibri.eldarovich99.advancedtodolist;

import android.app.Application;
import com.devcolibri.eldarovich99.advancedtodolist.di.components.AppComponent;
import com.devcolibri.eldarovich99.advancedtodolist.di.components.DaggerAppComponent;
import com.devcolibri.eldarovich99.advancedtodolist.di.components.NotesComponent;
import com.devcolibri.eldarovich99.advancedtodolist.di.modules.RoomModule;

public class Injector {
    private static NotesComponent notesComponent;
    private static AppComponent appComponent;

    public static void init(Application application) {
        appComponent = DaggerAppComponent.builder().roomModule(new RoomModule(application)).build();
    }

    public static NotesComponent getNotesComponent() {
        if(notesComponent == null) {
            notesComponent = appComponent.plusNoteComponent();
        }

        return notesComponent;
    }

    public static void destroyPersonComponent() {
        notesComponent = null;
    }

}