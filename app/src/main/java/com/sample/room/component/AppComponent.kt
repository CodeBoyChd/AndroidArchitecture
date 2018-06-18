package com.sample.room.component

import com.sample.room.MyApplication
import com.sample.room.module.*
import com.sample.room.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/** BindsInstance: Marks a method on a component builder or subcomponent builder that allows an instance to be bound to some type within the component. 
 *  Component.Builder: A builder for a component. Components may have a single nested static abstract class or interface annotated with @Component.Builder.
 *  If they do, then the component's generated builder will match the API in the type
 *  @BindsInstance methods should be preferred to writing a @Module with constructor arguments and immediately providing those values
 *
 *  Note that we need not specify Builder appModule(AppModule appModule); inside the Component.Builder anymore as we are going to let
 *  dagger use the default constructor of AppModule now.
 */
@ApplicationScope
@Component(modules = arrayOf(ActivityBuilderModule::class, AndroidInjectionModule::class,
        AppModule::class, PicassoModule::class, ServiceModule::class, SchedulerModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}