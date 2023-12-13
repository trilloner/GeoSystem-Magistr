import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../../service/impl/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../model/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;
  error: any;

  constructor(private authService: AuthenticationService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get inputs(): any {
    return this.registerForm.controls;
  }

  buildUser(): any {
    const user = {
      name: this.inputs.name.value,
      email: this.inputs.email.value,
      password: this.inputs.password.value
    };
    console.log(user);
    return user;
  }

  register(): void {
    this.authService.registration(this.buildUser()).subscribe(data => {
      this.router.navigate(['login']);
    }, error => {
      this.error = error;
    });
  }
}
