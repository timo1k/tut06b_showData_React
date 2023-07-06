function Posts({ imageSrc }) {
  return (
    <div className="posts">
      <div className="inner">
        <div>
          <img src={imageSrc}></img>
        </div>
        <div>
          <h2>TITLE</h2>
          <p>COMMENTS</p>
        </div>
      </div>
    </div>
  );
}
