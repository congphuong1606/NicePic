package ominext.android.vn.nicepic.di.component;

import dagger.Component;
import ominext.android.vn.nicepic.di.module.ApplicationModule;
import ominext.android.vn.nicepic.di.module.FirebaseModule;
import ominext.android.vn.nicepic.di.module.ViewModule;

/**
 * Created by MyPC on 24/07/2017.
 */

@Component(modules = {
        ApplicationModule.class,
        FirebaseModule.class,
        })
public interface ApplicationComponent {
    ViewComponent plus(ViewModule viewModule);
}
