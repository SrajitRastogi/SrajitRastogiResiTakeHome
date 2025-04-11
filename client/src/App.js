import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [photos, setPhotos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [downloadMessage, setDownloadMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/mars/photos/all')
      .then(response => {
        setPhotos(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching photos:', error);
        setErrorMessage('Failed to load photos.');
        setLoading(false);
      });
  }, []);

  const handleDownload = (imageUrl) => {
    setDownloadMessage('Downloading...');
    axios.post(`http://localhost:8080/mars/photos/download`, null, {
      params: { imageUrl }
    })
    .then(response => {
      setDownloadMessage(response.data); // ✅ Show backend message
    })
    .catch(error => {
      console.error('Error downloading photo:', error);
      setDownloadMessage('Failed to download image.');
    });
  };

  const groupPhotosByDate = (photos) => {
    return photos.reduce((grouped, photo) => {
      if (!grouped[photo.date]) {
        grouped[photo.date] = [];
      }
      grouped[photo.date].push(photo);
      return grouped;
    }, {});
  };

  const groupedPhotos = groupPhotosByDate(photos);

  return (
    <div className="App">
      <h1>Mars Rover Image Explorer 🚀</h1>

      {loading && <p>Loading photos...</p>}
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
      {downloadMessage && <p style={{ color: 'green' }}>{downloadMessage}</p>}

      {!loading && Object.keys(groupedPhotos).map(date => (
        <div key={date} className="photo-group">
          <h2>Photos from {date}</h2>
          <div className="photo-grid">
            {groupedPhotos[date].map((photo, index) => (
              <div key={index} className="photo-card">
                <img src={photo.imageUrl} alt={`Mars Rover - ${date}`} />
                <button onClick={() => handleDownload(photo.imageUrl)}>
                  Download
                </button>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
}

export default App;
