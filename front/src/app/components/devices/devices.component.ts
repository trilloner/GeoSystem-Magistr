import {Component, Inject, Injectable, OnInit, Renderer2} from '@angular/core';
import {DeviceService} from '../../service/impl/device.service';
import {Device} from '../../model/device';
import {HttpClient} from '@angular/common/http';
import {ParserResponse} from '../../model/parser-response';
import {MapComponent} from '../map/map.component';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class DevicesComponent implements OnInit {

  devices: Device[];
  fileToUpload: File = null;
  parserResponse: object[] = [];
  parserPoints: ParserResponse[] = [];

  constructor(private deviceService: DeviceService,
              private http: HttpClient,
              @Inject(DOCUMENT) private document: Document,
              private renderer2: Renderer2,) {
  }

  handleFileInput(files: FileList): void {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
  }

  ngOnInit(): void {
    this.getAllDevices();

    // adding dynamic js to angular component

  }

  getAllDevices(): void {
    this.deviceService.getAll().subscribe(devices => {
      this.devices = devices.List;
      console.log(this.devices);
    });
    this.addScript();
  }

  uploadFile(): void {
    this.deviceService.uploadFile(this.fileToUpload).subscribe(filename => {
      console.log(filename);
    }, error => console.log(error));
  }

  onUpload(): void {
    console.log(this.fileToUpload);
    this.deviceService.uploadFile(this.fileToUpload).subscribe((body) => {
        this.parserResponse = body;
        this.getPointsToMapComponent();
      }
    );


  }

  getPointsToMapComponent(): ParserResponse[] {

    this.parserResponse.forEach(item => {
      const info = {
        // @ts-ignore
        density: item.density,
        points: {
          // @ts-ignore
          first: item.points.first,
          // @ts-ignore
          second: item.points.second
        }
      } as ParserResponse;
      this.parserPoints.push(info);
    });
    console.log('DATA: ' + this.parserPoints);
    localStorage.setItem('parserPoints', JSON.stringify(this.parserPoints));
    return this.parserPoints;
  }

  addScript(): void {
    const script = this.renderer2.createElement('script');
    script.type = `application/javascript`;
    script.text = `
           document.querySelectorAll('input[name="light"]').forEach((elem) => {
            elem.onchange = () => {
            setTimeout(function () {
            document.querySelector('.first-block').style.display = "none";
            document.querySelector('.second-block').style.display = "block";
            }, 1000)
            }
            })

            document.querySelector('.second-block').onchange = () => {
            setTimeout(function () {
            document.querySelector('.second-block').style.display = "none";
            document.querySelector('.third-block').style.display = "block";
            }, 1000)
            }

            document.querySelector('.third-block').onchange = () => {
            setTimeout(function () {
            document.querySelector('.third-block').style.display = "none";
            }, 1000)
            }
        `;

    this.renderer2.appendChild(this.document.body, script);
  }

}
