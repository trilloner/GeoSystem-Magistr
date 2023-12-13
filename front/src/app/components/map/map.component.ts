import {Component, Injectable, NgZone, OnInit} from '@angular/core';
import {FieldService} from '../../service/impl/field.service';
import {FieldDTO} from '../../model/fieldDTO';
import {toNumbers} from '@angular/compiler-cli/src/diagnostics/typescript_version';
import {DevicesComponent} from '../devices/devices.component';
import {ParserResponse} from '../../model/parser-response';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class MapComponent implements OnInit {


  constructor(private zone: NgZone,
              private fieldService: FieldService,
              private deviceComponent: DevicesComponent) {

    this.mapTypeId = 'hybrid';
  }

  title = 'firstApp';
  markerIcon = 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|4286f4';
  lat: number;
  long: number;
  mapTypeId: string;
  map: any;
  mapClickListener: any;
  userGeolocation: any;
  geoJsonObj: any;
  geojson: any;
  pointList: { lat: number; lng: number }[] = [];
  field: FieldDTO;
  selectedPolygon: number;
  density: number;
  error: any;
  centralMarkers: number[];
  parserPoints: ParserResponse[] = [];
  isFinished = false;
  currentFieldId: number;

  ngOnInit(): void {
    this.getAllFields();
    this.generateGeolocation();
    this.parserPoints = JSON.parse(localStorage.getItem('parserPoints'));
    console.log(this.parserPoints);
  }


  getAllFields(): void {
    this.fieldService.getAll().subscribe(fields => {
      this.geojson = fields.FeatureCollection;
      console.log(this.geojson);
    });

  }

  mapHandler(map: google.maps.Map): void {
    this.map = map;
    this.mapClickListener = this.map.addListener('click', (e: google.maps.MouseEvent) => {
      this.zone.run(() => {
        this.lat = e.latLng.lat();
        this.long = e.latLng.lng();
        console.log(this.lat + ' and ' + this.long);
      });
    });
  }

  generateGeolocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position: Position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          this.lat = pos.lat;
          this.long = pos.lng;
          console.log(pos);
        },
        () => {
          // handleLocationError(true, infoWindow, map.getCenter());
        }
      );
    } else {
      // Browser doesn't support Geolocation
      // handleLocationError(false, infoWindow, map.getCenter());
    }

  }


  createNewPolygon(): void {
    this.userGeolocation = new google.maps.drawing.DrawingManager({
      drawingMode: google.maps.drawing.OverlayType.POLYGON,
      drawingControl: false,
      drawingControlOptions: {
        drawingModes: [google.maps.drawing.OverlayType.POLYGON],
      },
      polygonOptions: {
        draggable: false,
        editable: false
      }
    });
    this.userGeolocation.setMap(this.map);
    google.maps.event.addListener(
      this.userGeolocation,
      'overlaycomplete',
      (event) => {
        console.log(event.overlay.getPath().getArray());
        this.finishNewPolygon();
        if (event.type === google.maps.drawing.OverlayType.POLYGON) {
          const paths = event.overlay.getPaths();
          for (let p = 0; p < paths.getLength(); p++) {
            google.maps.event.addListener(
              paths.getAt(p),
              'set_at',
              () => {
                if (!event.overlay.drag) {
                  this.updatePointList(event.overlay.getPath());
                }
              }
            );
            google.maps.event.addListener(
              paths.getAt(p),
              'insert_at',
              () => {
                this.updatePointList(event.overlay.getPath());
              }
            );
            google.maps.event.addListener(
              paths.getAt(p),
              'remove_at',
              () => {
                this.updatePointList(event.overlay.getPath());
              }
            );
          }
          this.updatePointList(event.overlay.getPath());
        }
      }
    );
  }

  getInfoAboutPolygon(clickEvent): void {

    console.log(clickEvent.feature.getProperty('density'));
    console.log(clickEvent.feature.getId());
    this.selectedPolygon = clickEvent.feature.getId();
  }

  convertCoordToString(): string {
    const len = this.pointList.length;
    let polygonString = 'POLYGON((';
    for (let i = 0; i < len; i++) {
      if (i === 0) {
        polygonString = polygonString.concat(String(this.pointList[i].lng) + ' ' + String(this.pointList[i].lat) + '');
        continue;
      }
      polygonString = polygonString.concat(',');
      polygonString = polygonString.concat(String(this.pointList[i].lng) + ' ' + String(this.pointList[i].lat));
    }
    polygonString = polygonString.concat(',');
    polygonString = polygonString.concat(String(this.pointList[0].lng) + ' ' + String(this.pointList[0].lat));
    polygonString = polygonString.concat('))');
    return polygonString;
  }

  finishNewPolygon(): void {
    this.userGeolocation.setDrawingMode(null);
    this.isFinished = true;
  }

  updatePointList(path): void {
    this.pointList = [];
    const len = path.getLength();
    for (let i = 0; i < len; i++) {
      console.log(path.getAt(i).toJSON());
      this.pointList.push(
        path.getAt(i).toJSON()
      );
    }
  }

  saveNewPolygon(inputDensity: string): void {
    const temp = this.convertCoordToString();
    this.field = {
      density: Number(inputDensity),
      wktToGeom: temp
    };
    this.fieldService.saveField(this.field).subscribe(field => {
        console.log(field);
        // this.field = field;
        this.currentFieldId = field;
      },
      error => {
        this.error = error;
      });
  }

  styleFunc(): any {
    return ({
      fillColor: 'green',
      strokeWeight: 1
    });
  }
}


