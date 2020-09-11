import { Component } from '@angular/core';
import { AuthService } from '../../@core/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../client.service';
import { FormBuilder, Validators } from '@angular/forms';
import { CountryService } from '../country.service';
import { Country } from '../country';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html'
})
export class ClientDetailsComponent {

  new: boolean = false;
  clientId: string;
  countries: Country[];

  form = this.fb.group(
    {
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      userName: ['', Validators.required],
      email: [''],
      address: ['', Validators.required],
      countryCode: ['', [Validators.required]]
    }
  );

  constructor(private fb: FormBuilder,
              private clientService: ClientService,
              private countryService: CountryService,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) {

    this.countryService.getCountries().subscribe(result => {
      this.countries = result;
    })

    this.clientId = this.route.snapshot.paramMap.get('id');
    if (!this.clientId) {
      this.new = true;
      return;
    }

    this.clientService.getClientDetails(this.clientId).subscribe(result => {
      this.form.patchValue(result)
    }, error => {
      if (error.status === 403) {
        this.authService.logout();
        this.router.navigate(['/login']);
      }
    });
  }

  submit() {
    if (this.form.invalid) {
      return;
    }
    this.clientService.saveClient(this.form.value, this.clientId).subscribe(() => {
      this.router.navigateByUrl('/clients');
    })
  }

  get firstName() {return this.form.get('firstName')}

  get lastName() {return this.form.get('lastName')}

  get userName() {return this.form.get('userName')}

  get email() {return this.form.get('email')}

  get address() {return this.form.get('address')}

  get countryCode() {return this.form.get('countryCode')}
}
