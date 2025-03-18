import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RoleGuard } from './guards/role.guard';
import { AuthGuard } from './guards/auth.guard';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { CandidatFormComponent } from './components/candidat-form/candidat-form.component';
import { CandidatResultComponent } from './components/candidat-result/candidat-result.component';
import { CandidatsComponent } from './components/candidats/candidats.component';
import { ParcoursComponent } from './components/parcours/parcours.component';
import { EtablissementComponent } from './components/etablissement/etablissement.component';
import { SuccessComponent } from './components/success/success.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {path: 'register', component: RegisterComponent},
  { path: 'users', component: UserListComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } },
  { path: 'parcours', component: ParcoursComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } },
  { path: 'etablissements', component: EtablissementComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } },
  { path: 'candidate-form', component: CandidatFormComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'CANDIDAT' } },
  { path: 'candidat-result', component: CandidatResultComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'JURY' } },
  { path: 'candidats', component: CandidatsComponent, canActivate: [AuthGuard, RoleGuard], data: { role: ['AGENT','JURY'] } },
  { path: 'welcome', component: WelcomeComponent },
  {path: 'success', component: SuccessComponent},
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: '**', redirectTo: '/welcome' },
];
  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
