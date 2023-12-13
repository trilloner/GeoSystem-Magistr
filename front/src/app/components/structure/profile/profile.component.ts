import {Component, OnInit} from '@angular/core';
import {User} from '../../../model/user';
import {AuthenticationService} from '../../../service/impl/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;

  constructor(private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(this.authService.currentUserValue);
    console.log(this.currentUser);
  }

  logout(): any {
    console.log('works');
    this.authService.logout();
    return;
  }

}
