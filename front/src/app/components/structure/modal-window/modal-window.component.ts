import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {LocationService} from '../../../service/impl/location.service';
import {Location} from '../../../model/location';
import {MapComponent} from '../../map/map.component';

@Component({
  selector: 'app-modal-window',
  templateUrl: './modal-window.component.html',
  styleUrls: ['./modal-window.component.css']
})
export class ModalWindowComponent implements OnInit {
  @ViewChild('modal') public modal: ElementRef;

  @Input() fieldId: number;
  location: Location;
  device = 'device';
  step2 = false;
  locationName: string;
  locationCity: string;

  constructor(private locationService: LocationService,
              private mapComponent: MapComponent) {
  }

  ngOnInit(): void {
    this.getLocationByField();
  }

  closeModal(): void {
    this.modal.nativeElement.style.display = 'none';
  }

  getLocationByField(): void {
    this.locationService.getLocationByFieldId(this.fieldId).subscribe(location => {
      this.location = location;
      console.log(this.location);

    });
  }

  saveField(density: string): void {
    console.log(density);
    this.mapComponent.saveNewPolygon(density);
  }

  saveLocation(locationDescription: string): void {
    this.location = {
      name: this.locationName,
      city: this.locationName,
      description: locationDescription,
      fieldId: this.mapComponent.currentFieldId,
      userByUserId: 1
    };
    this.locationService.saveLocation(this.location).subscribe(location => {
      console.log(location);
      this.location = location;
    });
  }

  saveInfo(name: string, city: string): void {
    this.locationName = name;
    this.locationCity = city;
    console.log(name, city);
  }
}
