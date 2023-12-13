import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Location} from '../../model/location';
import {LocationService} from '../../service/impl/location.service';
import {MapComponent} from '../map/map.component';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {

  locations: Location[];
  selectedLocation: Location;
  @ViewChild('modal') public modal: ElementRef;

  constructor(private locationService: LocationService,
              private mapComponent: MapComponent) {
  }

  ngOnInit(): void {
    this.getAllLocations();
  }

  getAllLocations(): void {
    this.locationService.getAll().subscribe(locations => {
      this.locations = locations.List;
    });
  }

  findLocationById(id: number): Location {
    for (const index in this.locations) {
      if (this.locations[index].id === id) {
        return this.locations[index];
      }
    }
  }

  sayHi(): void {
    this.mapComponent.createNewPolygon();
  }

  closeModal(): void {
    this.modal.nativeElement.style.display = 'none';
  }

  openModal(id: number): void {

    this.modal.nativeElement.style.display = 'block';
    this.selectedLocation = this.findLocationById(id);
    console.log(this.selectedLocation);
  }
}
