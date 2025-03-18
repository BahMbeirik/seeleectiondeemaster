import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar-admin',
  templateUrl: './navbar-admin.component.html',
  styleUrls: ['./navbar-admin.component.css']
})
export class NavbarAdminComponent implements OnInit {
  isLoggedIn$: Observable<boolean>;
  isMobileMenuOpen = false;
  isUserMenuOpen = false;
  userName = 'Utilisateur';
  userEmail = 'utilisateur@exemple.com';

  constructor(private authService: AuthService, private router: Router) {
    this.isLoggedIn$ = this.authService.isLoggedIn();
  }

  ngOnInit(): void {
    
  }

  toggleMobileMenu(): void {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
    if (this.isMobileMenuOpen) {
      this.isUserMenuOpen = false;
    }
  }

  toggleUserMenu(): void {
    this.isUserMenuOpen = !this.isUserMenuOpen;
  }

  logout(): void {
    this.authService.logout();
    this.isUserMenuOpen = false;
    this.isMobileMenuOpen = false;
    this.router.navigate(['/']);
  }

  // Vérifier si l'utilisateur est un administrateur
  isAdmin(): boolean {
    const role = this.authService.getCurrentUserRole();
    return role === 'ADMIN';
  }

  isJury(): boolean {
    const role = this.authService.getCurrentUserRole();
    return role === 'JURY';
  }

}
