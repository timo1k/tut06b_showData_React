function FindUserObj() {
  const ref = React.useRef(null);

  React.useEffect(() => {
    const myDomElement = find();
    ref.current.appendChild(myDomElement);
  }, []);

  return <div ref={ref}></div>;
}
