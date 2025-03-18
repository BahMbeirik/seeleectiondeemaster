import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const user = this.authService.getCurrentUser();

    if (user) {
      // Redirect based on role when trying to access /login or /
      if (state.url === '/login' || state.url === '/') {
        this.redirectToHome(user.role);
        return false;
      }
      return true;
    }

    // Redirect unauthenticated users to /welcome
    if (state.url !== '/welcome') {
      this.router.navigate(['/welcome']);
    }
    return false;
  }

  private redirectToHome(role: string) {
    switch (role) {
      case 'ADMIN':
        this.router.navigate(['/users']);
        break;
      case 'CANDIDAT':
        this.router.navigate(['/candidate-form']);
        break;
      case 'JURY':
        this.router.navigate(['/candidat-result']);
        break;
      case 'AGENT':
        this.router.navigate(['/candidats']);
        break;
      default:
        this.router.navigate(['/welcome']);
        break;
    }
  }
}
