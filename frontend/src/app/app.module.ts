import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { NavbarAdminComponent } from './components/navbar-admin/navbar-admin.component';
import { AuthService } from './services/auth.service';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { CandidatFormComponent } from './components/candidat-form/candidat-form.component';
import { CandidatResultComponent } from './components/candidat-result/candidat-result.component';
import { CandidatsComponent } from './components/candidats/candidats.component';
import { ParcoursComponent } from './components/parcours/parcours.component';
import { EtablissementComponent } from './components/etablissement/etablissement.component';
import { SuccessComponent } from './components/success/success.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserListComponent,
    NavbarAdminComponent,
    WelcomeComponent,
    UnauthorizedComponent,
    CandidatFormComponent,
    CandidatResultComponent,
    CandidatsComponent,
    ParcoursComponent,
    EtablissementComponent,
    SuccessComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    // Intercepteur pour ajouter le token JWT aux requêtes
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    AuthService
  ],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
