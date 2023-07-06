function Container() {
  return (
    <div className="container">
      <div className="left">
        <div className="inner">
          <h2>Left</h2>
        </div>
      </div>
      <div className="center">
        <div className="inner">
          <h2>Center</h2>
          {/* create a component that creates a list of Posts */}
          <Posts
            imageSrc={
              "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
            }
          ></Posts>
          <Posts
            imageSrc={
              "https://m.media-amazon.com/images/M/MV5BMGM2MzA5YzYtODc0Ni00ZjU4LWI4ZmUtZGJjNGU0ODY1MGJkXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_FMjpg_UX1000_.jpg"
            }
          ></Posts>
          <Posts></Posts>
          <Posts></Posts>
          <Posts></Posts>
          <Posts></Posts>
          <Posts></Posts>
          <Posts></Posts>
        </div>
      </div>
      <div className="right">
        <div className="inner">
          <h2>Right</h2>
        </div>
      </div>
    </div>
  );
}
