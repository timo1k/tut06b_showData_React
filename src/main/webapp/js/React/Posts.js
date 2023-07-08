function Posts({ imageSrc }) {
  return (
    <div className="posts">
      <div className="inner">
        <img src={imageSrc}></img>
        <div>
          <h2>TITLE</h2>
          <p>COMMENTS</p>
        </div>
      </div>
    </div>
  );
}
