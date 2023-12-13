import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {DevicesComponent} from './components/devices/devices.component';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './components/structure/header/header.component';
import {MapComponent} from './components/map/map.component';
import {AgmCoreModule} from '@agm/core';
import {FieldComponent} from './components/field/field.component';
import {LocationComponent} from './components/location/location.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ModalWindowComponent} from './components/structure/modal-window/modal-window.component';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/structure/login/login.component';
import {GuardService} from './service/impl/guard.service';
import {RegistrationComponent} from './components/structure/registration/registration.component';
import {ProfileComponent} from './components/structure/profile/profile.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'login'},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: MapComponent, canActivate: [GuardService]},
  {path: 'devices', component: DevicesComponent, canActivate: [GuardService]},
  {path: 'registration', component: RegistrationComponent},
  {path: 'cabinet', component: ProfileComponent, canActivate: [GuardService]}
];


@NgModule({
  declarations: [
    AppComponent,
    DevicesComponent,
    HeaderComponent,
    MapComponent,
    FieldComponent,
    LocationComponent,
    ModalWindowComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCsLHgXitFI3gpkYaDEv42uSVApQ_vPHQE',
      libraries: ['drawing']
    }),
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
