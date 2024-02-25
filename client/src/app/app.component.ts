import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'client';
  userProfile: any
  /**
   *
   */
  constructor(public keycloakService: KeycloakService) {
    if (this.keycloakService.isLoggedIn())
      this.keycloakService.loadUserProfile().then(
        profil => this.userProfile = profil
      )
  }

  async handleLogin() {
    await this.keycloakService.login({
      redirectUri: window.location.origin
    })
  }
  handleLogout() {
    this.keycloakService.logout(window.location.origin)
  }
}
