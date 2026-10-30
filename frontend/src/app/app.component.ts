import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkationService } from './workation.service';
import { Workation, SortKey } from './workation.model';
@Component({selector:'app-root',standalone:true,imports:[CommonModule],templateUrl:'./app.component.html',styleUrl:'./app.component.css'})
export class AppComponent implements OnInit {
 private service=inject(WorkationService);
 workations:Workation[]=[]; loading=true; error=''; sortKey:SortKey='employee'; direction:1|-1=1;
 readonly columns:{key:SortKey;label:string}[]=[
  {key:'employee',label:'Employee'}, {key:'origin',label:'Origin'},
  {key:'destination',label:'Destination'}, {key:'start',label:'Start'},
  {key:'end',label:'End'}, {key:'workingDays',label:'Working days'}, {key:'risk',label:'Risk'}
 ];
 ngOnInit(){this.load();}
 load(){this.loading=true;this.error='';this.service.list().subscribe({next:data=>{this.workations=data;this.loading=false;},error:()=>{this.error='Could not load workations. Check that the Spring Boot API is running on port 8080.';this.loading=false;}});}
 sort(key:SortKey){if(this.sortKey===key){this.direction=this.direction===1?-1:1;}else{this.sortKey=key;this.direction=1;}}
 get sorted():Workation[]{const key=this.sortKey;const direction=this.direction;return [...this.workations].sort((a,b)=>{
  const x=a[key],y=b[key];return direction*(typeof x==='number'&&typeof y==='number'?x-y:String(x).localeCompare(String(y),undefined,{numeric:true,sensitivity:'base'}));
 });}
 sortSymbol(key:SortKey){return this.sortKey!==key?'↕':this.direction===1?'↑':'↓';}
 ariaSort(key:SortKey):'ascending'|'descending'|'none'{return this.sortKey!==key?'none':this.direction===1?'ascending':'descending';}
 displayDate(value:string){if(!/^\d{4}-\d{2}-\d{2}$/.test(value))return value;const [year,month,day]=value.split('-');return `${day}/${month}/${year}`;}
 riskText(risk:string){return ['LOW','NO','LOW_RISK','NO_RISK'].includes(risk.toUpperCase())?'No risk':'High risk';}
 riskIcon(risk:string){const r=risk.toUpperCase();return r==='NO'||r==='NO_RISK'?'green-risk.svg':r==='LOW'||r==='LOW_RISK'?'yellow-risk.svg':'red-risk.svg';}
 riskClass(risk:string){const r=risk.toUpperCase();return r==='NO'||r==='NO_RISK'?'no':r==='LOW'||r==='LOW_RISK'?'low':'high';}
 flagPath(country:string){const codes:Record<string,string>={'Germany':'de','United States':'us','Ukraine':'ua','Belgium':'be','Spain':'es','Greece':'gr','India':'in'};return '/flags/'+(codes[country]??'unknown')+'.svg';}
 trackById(_index:number,item:Workation){return item.workationId;}
}
