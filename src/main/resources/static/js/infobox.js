function InfoBox(opts) {
	google.maps.OverlayView.call(this);
	this.latlng_ = opts.latlng;
	this.map_ = opts.map;
	this.clientid = opts.clientid;
	this.timerschedule = opts.timerschedule;
	this.offsetVertical_ = -230;
	this.offsetHorizontal_ = 0;
	this.height_ = 210;
	this.width_ = 220;

	var me = this;
	me.panMap.apply(me);
	this.setMap(this.map_);
}

InfoBox.prototype = new google.maps.OverlayView();

InfoBox.prototype.remove = function() {
  if (this.div_) {
    this.div_.parentNode.removeChild(this.div_);
    this.div_ = null;
  }
};

InfoBox.prototype.draw = function() {
	this.createElement();
	var pixPosition = this.getProjection().fromLatLngToDivPixel(this.latlng_);
	if (!pixPosition) return;
	this.div_.style.width = this.width_ + "px";
	this.div_.style.left = (pixPosition.x + this.offsetHorizontal_) + "px";
	this.div_.style.height = this.height_ + "px";
	this.div_.style.top = (pixPosition.y + this.offsetVertical_) + "px";
	this.div_.style.display = 'block';
};

InfoBox.prototype.createElement = function() {
	var panes = this.getPanes();
	var div = this.div_;
	if (!div) {
		div = this.div_ = document.createElement("div");
		div.style.border = "0px none";
    div.style.position = "absolute";
    
    div.style.background = "url('images/callout.png')";
	//div.style.width = this.width_ + "px";
	//div.style.height = this.height_ + "px";
	
	var contentDiv = document.createElement("div");
	contentDiv.style.padding = "2px";
	contentDiv.style.width="100px";
	contentDiv.style.align="center";
	contentDiv.style.left="65px";
	contentDiv.style.position = "absolute";
	
	var liftoffTime = new Date();
	var sec = liftoffTime.getSeconds();
	liftoffTime.setSeconds(sec+this.timerschedule);
    $(contentDiv).countdown({until: liftoffTime, format: 'MS'});
    
    var clientDiv = document.createElement("div");
    clientDiv.innerHTML=this.clientid;
    clientDiv.style.padding = "5px";
    clientDiv.style.left="50px";
    clientDiv.style.top="0px";
    clientDiv.style.textAlign = "center";
    
    
    
    var topDiv = document.createElement("div");
    topDiv.style.textAlign = "right";
    var closeImg = document.createElement("img");
    closeImg.style.width = "32px";
    closeImg.style.height = "32px";
    closeImg.style.cursor = "pointer";
    closeImg.src = "images/closebigger.gif";
    topDiv.style.position = "absolute";
    topDiv.style.right = "0";
    topDiv.appendChild(closeImg);

    function removeInfoBox(ib) {
      return function() {
        ib.setMap(null);
      };
    }

    google.maps.event.addDomListener(closeImg, 'click', removeInfoBox(this));

    div.appendChild(topDiv);
    div.appendChild(clientDiv);
    div.appendChild(contentDiv);
    div.style.display = 'none';
    panes.floatPane.appendChild(div);
    this.panMap();
  } else if (div.parentNode != panes.floatPane) {
    // The panes have changed.  Move the div.
    div.parentNode.removeChild(div);
    panes.floatPane.appendChild(div);
  } else {
    // The panes have not changed, so no need to create or move the div.
  }
}

InfoBox.prototype.panMap = function() {
	var map = this.map_;
	var bounds = map.getBounds();
	if (!bounds)
		return;

	// The position of the infowindow
	var position = this.latlng_;

	// The dimension of the infowindow
	var iwWidth = this.width_;
	var iwHeight = this.height_;

	// The offset position of the infowindow
	var iwOffsetX = this.offsetHorizontal_;
	var iwOffsetY = this.offsetVertical_;

	// Padding on the infowindow
	var padX = 40;
	var padY = 40;

	// The degrees per pixel
	var mapDiv = map.getDiv();
	var mapWidth = mapDiv.offsetWidth;
	var mapHeight = mapDiv.offsetHeight;
	var boundsSpan = bounds.toSpan();
	var longSpan = boundsSpan.lng();
	var latSpan = boundsSpan.lat();
	var degPixelX = longSpan / mapWidth;
	var degPixelY = latSpan / mapHeight;

	// The bounds of the map
	var mapWestLng = bounds.getSouthWest().lng();
	var mapEastLng = bounds.getNorthEast().lng();
	var mapNorthLat = bounds.getNorthEast().lat();
	var mapSouthLat = bounds.getSouthWest().lat();

	// The bounds of the infowindow
	var iwWestLng = position.lng() + (iwOffsetX - padX) * degPixelX;
	var iwEastLng = position.lng() + (iwOffsetX + iwWidth + padX) * degPixelX;
	var iwNorthLat = position.lat() - (iwOffsetY - padY) * degPixelY;
	var iwSouthLat = position.lat() - (iwOffsetY + iwHeight + padY) * degPixelY;

	// calculate center shift
	var shiftLng = (iwWestLng < mapWestLng ? mapWestLng - iwWestLng : 0)
			+ (iwEastLng > mapEastLng ? mapEastLng - iwEastLng : 0);
	var shiftLat = (iwNorthLat > mapNorthLat ? mapNorthLat - iwNorthLat : 0)
			+ (iwSouthLat < mapSouthLat ? mapSouthLat - iwSouthLat : 0);

	// The center of the map
	var center = map.getCenter();

	// The new map center
	var centerX = center.lng() - shiftLng;
	var centerY = center.lat() - shiftLat;

	// center the map to the new shifted center
	map.setCenter(new google.maps.LatLng(centerY, centerX));

	// Remove the listener after panning is complete.
	//google.maps.event.removeListener(this.boundsChangedListener_);
	//this.boundsChangedListener_ = null;
  
};

